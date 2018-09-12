# X-API

## Introduction:
To create a middleware server that accepts requests from client and process that request. The processed request is further sent to the API vendor of the client's choice to get a response. The response received will be processed and parsed into a particular JSON format. The parsed format will then be sent back to the client as the response to its request. The middleware server will have its own request and response format, the client needs to have the application with respect to that request and response format.

## Problem Statement:
* Any Artificial Intelligence service provider is not perfect, if one is good in a particular task, then it might lack in any another task.
* Any client application using these AI API needs to choose one vendor and stick to it because changing the vendor will require to do major changes to the application.

## Objectives:
* Allow client applications to easily port from one API vendor to another.
* Allow client applications to use different vendors for different services.

## Methodology:
<img src="https://github.com/nkg447/x-api/raw/master/Client-server-model.png" width="100%"><br>
* Create a middleware server that will act as a moderator between the client and the AI API vendor. The client application only needs to specify the API vendor name so as to use that API vendor. The using of the vendor will all be handled by the middleware server, further allowing the client application to be more functionality centric.
* Have our own request and response format for APIs.
* Process the request coming from the user and further send that request to the API vendor of client's choice.
