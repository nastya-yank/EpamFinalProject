package com.golubeva.project.controller.command;

import com.golubeva.project.controller.command.type.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * The {@code CommandProvider} class represents command provider.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private CommandProvider() {
    }

    /**
     * Define command.
     *
     * @param commandName the command name
     * @return the optional of created command
     */
    public static Optional<CustomCommand> defineCommand(String commandName) {
        Optional<CustomCommand> currentCommand;
        if (commandName != null && !commandName.isBlank()) {
            try {
                CommandType currentType = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = Optional.of(currentType.getCommand());
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.ERROR, "Incorrect command type", commandName, e);
                currentCommand = Optional.empty();
            }
        } else {
            currentCommand = Optional.empty();
        }
        return currentCommand;
    }
}
