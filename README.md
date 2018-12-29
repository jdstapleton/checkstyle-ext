# Checkstyle Checks
- ReturnSeparationCheck - ensures that a return statement has a new line before it, if its not the first statement in a block.
- MultilineStatementCheck - A multiline statement or block, must be preceeded by an empty line.  Optionally allowing a variable definition to be immediately before it

```
<module name="ReturnSeparation"/>
<module name="MultilineStatement">
  <property name="allowVarDefBeforeBlock" value="True"/>
</module>
```
