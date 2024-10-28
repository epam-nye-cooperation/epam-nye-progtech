package hu.nye.creational;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

public class DocumentEditorFactory {
    public static DocumentEditor create() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return new Notepad();
        } else if (SystemUtils.IS_OS_MAC) {
            return new TextEdit();
        } else if (SystemUtils.IS_OS_LINUX) {
            return new Gedit();
        } else {
            return new DocumentEditor() {
                // Default implementation
            };
        }
    }

    public interface DocumentEditor {
        @SneakyThrows
        default void open(File path) {
            String executable = getExecutable();
            if (StringUtils.isBlank(executable)) {
                throw new IllegalStateException("No default editor command is supported");
            }
            Runtime.getRuntime().exec(new String[]{executable, path.getAbsolutePath()});
        }

        default String getExecutable() {
            return System.getenv("EDITOR");
        }
    }

    private static class Notepad implements DocumentEditor {
        public String getExecutable() {
            return "notepad";
        }
    }

    private static class TextEdit implements DocumentEditor {
        public String getExecutable() {
            return "textedit";
        }
    }

    private static class Gedit implements DocumentEditor {
        public String getExecutable() {
            return "gedit";
        }
    }
}