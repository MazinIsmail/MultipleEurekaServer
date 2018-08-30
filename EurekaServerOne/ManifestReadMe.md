cf push
Using manifest file /path-to-current-working-directory/manifest.yml

cf push -f ./different-directory-1/different-directory-2/alternate_manifest.yml
Using manifest file ./different-directory-1/different-directory-2/alternate_manifest.yml

cf push -f ./different-directory-1/different-directory-2/
Using manifest file ./different-directory-1/different-directory-2/manifest.yml

Manifests are written in YAML.

The manifest begins with three dashes.
The applications block begins with a heading followed by a colon.
The application name is preceded by a single dash and one space.
Subsequent lines in the block are indented two spaces to align with name.

Note: A minimal manifest requires only an application name.


cf push follows rules of precedence when setting attribute values:

Manifests override most recent values, including defaults.
Command line options override manifests.

