# Checkstyle Checks
- ReturnSeparationCheck - ensures that a return statement has a new line before it, if its not the first statement in a block.
- MultilineStatementCheck - A multiline statement or block, must be preceeded by an empty line.  Optionally allowing a variable definition to be immediately before it

```
<module name="ReturnSeparation"/>
<module name="MultilineStatement">
  <property name="allowVarDefBeforeBlock" value="True"/>
</module>
```

## Signing
- Requires gpg to be installed and a key to be in the keychain
- Requires that the gpg-agent to be installed, and the password to be cached (non-interactive)
- Requires that the `$USER_HOME/.gradle/gradle.properties` to be configured with these options:

    signing.gnupg.executable=/usr/local/bin/gpg
    signing.gnupg.optionsFile=/$HOME/.gnupg/gpg.conf
    signing.gnupg.keyName=0xF1CAB289

- Requires the version to be a released version
