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
            "face_location": {
                "width": 56.0,
                "height": 66.0,
                "left": 214.0,
                "top": 87.0
            }
        },
        .
        .
        .
        
    ]
}
```