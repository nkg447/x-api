# Request and Response formats

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
    "from": "source language"
    "text": "text who's language is to be translated"
    "to": "target language"
}
```
#### Response - 
```
{
    "translatedText": "text after translation"
}
```
