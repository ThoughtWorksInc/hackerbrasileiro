[![Build Status](https://snap-ci.com/6HK4SqNeCxvb15vzCE4EfxWzz7l6G6gXFHoryMOLJig/build_image)](https://snap-ci.com/ThoughtWorksInc/hackerbrasileiro/branch/master)

## Requirements to execute the application

### Set up environment variables

Open your bashrc file (or zshrc or other shell that you are using) with a editor text and insert the code below:

```
export HACKERBRASILEIRO_PORT=<port_number>
export HACKERBRASILEIRO_PHOTO_PATH="<photo_path>"
export HACKERBRASILEIRO_FILE_PATH="<file_path>"
export HACKERBRASILEIRO_FACEMORPHER_PATH="<facemorpher_path>"
export HACKERBRASILEIRO_USERNAME="<username>"
export HACKERBRASILEIRO_PASSWORD="<password>"
export HACKERBRASILEIRO_LOG_PATH="<log_path>"
```

After save the file, it is necessary update your shell, for example:
```
$ source ~/.bashrc
```

```
$ source ~/.zshrc
```

## Run application

```
$ ./gradlew bootRun
```


