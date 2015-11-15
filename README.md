### Chipmunk2D-JNA
A JNA-based wrapper for Chipmunk2D

### Usage

##### To see it working
* Put `libchipmnuk.so` ( or `.dll` or `.dylib` ) into `demo/src/main/resources` folder
* `gradle demo:run`

`HelloChipmunk` mimics the tutorial code found at the beginning of Chipmunk's docs
https://chipmunk-physics.net/release/ChipmunkLatest-Docs/#Intro-HelloChipmunk

##### Building and using the library
* `gradle library:publishToMavenLocal`
* Add the artifact as dependency to your projects:
    
    dependencies {
        compile group: 'com.dewdropgames.chipmunk2d-jna', name: 'chipmunk2d-jna', version: '0.1.0'
    }
