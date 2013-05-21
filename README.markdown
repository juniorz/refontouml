# RefOntoUML

This is a fork of https://code.google.com/p/rcarraretto/

## Changes

https://github.com/juniorz/refontouml/compare/4f125d5...master

## Instructions

**Requirements**  
[Eclipse Galileo SR2 (Modeling Tools)](http://www.eclipse.org/downloads/packages/release/galileo/sr2)

This version is required because this project uses an older version of OCL for Eclipse.  
More info: http://www.eclipse.org/articles/Article-EMF-Codegen-with-OCL/ and http://www.eclipse.org/forums/index.php/t/206987/

1. Open the project `br.ufes.inf.nemo.ontouml.refontouml`
2. Make sure the `MANIFEST.MF` has the Java version set to > 1.5 (otherwise the JEmitters won't work.)
3. Make sure your workspace is compliant with Java 1.5+ (because annotations are deprecated in Java 1.6 strict)  
     To change it go to: Preferences > Java > Compiler > Compiler compliance level: 1.5
4. Open the genmodel
5. Generate the model code
6. Export the plugin

The exported plugin WON'T WORK on Eclipse Indigo. It doesn't even show in the
plugin list. Maybe there is a broken dependency.
