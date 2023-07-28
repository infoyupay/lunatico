/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.toolbox;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 * System of local user files.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class LocalFiles {
    /**
     * The user home path.
     */
    private static Path userHome,
    /**
     * The yupay path (user_Home/.yupay)
     */
    yupay,
    /**
     * The userHome/.yupay/moony path.
     */
    moony,
    /**
     * The cnx file path inside {@link #moony}
     */
    cnx;


    /**
     * Private constructor that prevents class instanciation.
     *
     * @throws IllegalAccessException always.
     */
    @Contract("->fail")
    private LocalFiles() throws IllegalAccessException {
        throw new IllegalAccessException("Shouldn't instanciate this class.");
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #userHome}
     * @throws IOException if can't create directories.
     */
    @NotNull
    public static Path getUserHome() throws IOException {
        if (userHome == null) {
            scanPaths();
        }
        return userHome;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #yupay}
     * @throws IOException if can't create directories.
     */
    @NotNull
    public static Path getYupay() throws IOException {
        if (yupay == null) {
            scanPaths();
        }
        return yupay;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #moony}
     * @throws IOException if can't create directories.
     */
    @NotNull
    public static Path getMoony() throws IOException {
        if (moony == null) {
            scanPaths();
        }
        return moony;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #cnx}
     * @throws IOException if can't create directories.
     */
    @NotNull
    public static Path getCnx() throws IOException {
        if (cnx == null) {
            scanPaths();
        }
        return cnx;
    }

    /**
     * Triggers a cycle of paths scanning, so all mapped
     * paths are updated.
     *
     * @throws IOException if cannot create directories.
     */
    public static void scanPaths() throws IOException {
        userHome = Path.of(System.getProperty("user.home"));
        yupay = userHome.resolve(".yupay");
        moony = yupay.resolve("moony");
        if (Files.notExists(moony)) Files.createDirectories(moony);
        cnx = moony.resolve("cnx.properties");
    }

    /**
     * Scans the connection properties from cnx.properties file.
     *
     * @return the connection properties.
     * @throws IOException if unable to read the cnx.properties file.
     */
    public static @NotNull Properties scanCnx() throws IOException {
        var r = new Properties();
        try (var is = Files.newInputStream(getCnx(), StandardOpenOption.READ)) {
            r.load(is);
        }
        return r;
    }
}
