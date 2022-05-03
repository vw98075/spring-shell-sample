package com.example.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ShellComponent
public record LocalDirectoryCommand(LoginService service) {

    @ShellMethod("List local Directories")
    public String ls(String dir) {

        String content = "";
        String newLine = System.getProperty("line.separator");
        Set<String> s = new HashSet<>();
        try (Stream<Path> stream = Files.walk(Paths.get(dir), 1)) {
            s = stream.filter(Files::isDirectory).map(Path::toAbsolutePath).map(Path::toString).collect(Collectors.toSet());
        } catch (IOException ex) {
        }

        Iterator<String> itr = s.iterator();
        while (itr.hasNext()) {
            content += itr.next() + "\n";
        }
        return content;
    }

    public Availability lsAvailability() {
        return this.service.isLoggedIn() ? Availability.available() : Availability.unavailable("Not log in yet");
    }
}
