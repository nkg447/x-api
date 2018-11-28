# Request and Response formats

## Endpoint -
```
endpoint - "https://x-api.herokuapp.com"
* add sub address written in brackets of APIs to the above endpoint to acess that API.
```

## Language


### Detect Language ("/detectLanguage")

#### Request - 
```
{
  "text": "text who's language is to be detected"
}
```
#### Response - 
```
{
    "detectedLanguage": "en"
}
```

### Translate Language ("/translate")

#### Request - 
```
{
    "from": "source language",
    "text": "text who's language is to be translated",
    "to": "target language"
}
```
#### Response - 
```
{
    "translatedText": "text after translation"
}
```

### Supported Languages ("/languages")

#### Request - 
```
GET / POST request to  -  "/languages" path
```
#### Response - 
```
{
    languages: [
        {
            name: "language name",
            code: "language code"
        },
        {
            name: "language name",
            code: "language code"
        },
        .
        .
        .
    ]
}
```

## Speech


### Text to Speech ("/tts")

#### Request - 
```
{
  "text": "text whos speech is to be created",
}
```
#### Response - 
```
{
}
data - a stream of audio
```

### Speech to Text ("/stt")

#### Request - 
```
data - stream of audio
```
#### Response - 
```
{
    "transcript": "text to the audio",
    "confidence": 1.0 // if confidence provided by vendor
}
```


## Vision


### Face Detect ("/faceDetect")

#### Request - 
```
{
  "url": "url to the image"
}
```
#### Response - 
```
{
    "faces": [
        {
            "width": 56.0,
            "height": 66.0,
            "left": 214.0,
            "top": 87.0
        },
        .
        .
        .
        
    ]
}
```