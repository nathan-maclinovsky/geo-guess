{ pkgs }: {
    deps = [
        pkgs.neovim
        pkgs.neovim
        pkgs.gradle
        pkgs.graalvm17-ce
        pkgs.maven
        pkgs.replitPackages.jdt-language-server
        pkgs.replitPackages.java-debug
    ];
}