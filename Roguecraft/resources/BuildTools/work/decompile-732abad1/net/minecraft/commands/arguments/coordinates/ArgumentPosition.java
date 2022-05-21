package net.minecraft.commands.arguments.coordinates;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandDispatcher;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.ICompletionProvider;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.world.level.World;

public class ArgumentPosition implements ArgumentType<IVectorPosition> {

    private static final Collection<String> EXAMPLES = Arrays.asList("0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "~0.5 ~1 ~-5");
    public static final SimpleCommandExceptionType ERROR_NOT_LOADED = new SimpleCommandExceptionType(new ChatMessage("argument.pos.unloaded"));
    public static final SimpleCommandExceptionType ERROR_OUT_OF_WORLD = new SimpleCommandExceptionType(new ChatMessage("argument.pos.outofworld"));
    public static final SimpleCommandExceptionType ERROR_OUT_OF_BOUNDS = new SimpleCommandExceptionType(new ChatMessage("argument.pos.outofbounds"));

    public ArgumentPosition() {}

    public static ArgumentPosition blockPos() {
        return new ArgumentPosition();
    }

    public static BlockPosition getLoadedBlockPos(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        BlockPosition blockposition = ((IVectorPosition) commandcontext.getArgument(s, IVectorPosition.class)).getBlockPos((CommandListenerWrapper) commandcontext.getSource());

        if (!((CommandListenerWrapper) commandcontext.getSource()).getLevel().hasChunkAt(blockposition)) {
            throw ArgumentPosition.ERROR_NOT_LOADED.create();
        } else if (!((CommandListenerWrapper) commandcontext.getSource()).getLevel().isInWorldBounds(blockposition)) {
            throw ArgumentPosition.ERROR_OUT_OF_WORLD.create();
        } else {
            return blockposition;
        }
    }

    public static BlockPosition getSpawnablePos(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
        BlockPosition blockposition = ((IVectorPosition) commandcontext.getArgument(s, IVectorPosition.class)).getBlockPos((CommandListenerWrapper) commandcontext.getSource());

        if (!World.isInSpawnableBounds(blockposition)) {
            throw ArgumentPosition.ERROR_OUT_OF_BOUNDS.create();
        } else {
            return blockposition;
        }
    }

    public IVectorPosition parse(StringReader stringreader) throws CommandSyntaxException {
        return (IVectorPosition) (stringreader.canRead() && stringreader.peek() == '^' ? ArgumentVectorPosition.parse(stringreader) : VectorPosition.parseInt(stringreader));
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        if (!(commandcontext.getSource() instanceof ICompletionProvider)) {
            return Suggestions.empty();
        } else {
            String s = suggestionsbuilder.getRemaining();
            Object object;

            if (!s.isEmpty() && s.charAt(0) == '^') {
                object = Collections.singleton(ICompletionProvider.b.DEFAULT_LOCAL);
            } else {
                object = ((ICompletionProvider) commandcontext.getSource()).getRelevantCoordinates();
            }

            return ICompletionProvider.suggestCoordinates(s, (Collection) object, suggestionsbuilder, CommandDispatcher.createValidator(this::parse));
        }
    }

    public Collection<String> getExamples() {
        return ArgumentPosition.EXAMPLES;
    }
}
