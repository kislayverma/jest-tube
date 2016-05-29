# jest-tube
This is a test tube repository for my experiments in Java. 

## Spring deferred result based reverse proxy
This is an effort to build a non-blocking, scalable reverse proxy using Spring 3's DeferredResult construct. As of now this works but I have not yet benchmarked it fully.

## Apache OpenNLP based text classifier
Originally built for the Hackerramp 2015 (Myntra hackday), this is an attempt to build an auto-text classifer and response builder that would serve as the potential core component of a chat bot. Some complete fabricated sample data is supplied for out-of-the-box testing

## jesttube-service
This is not an experiment but an easy way to deploy should any of the components need deployment to be tested (as is needed for the rev. proxy). This will build a war and all that's needed is to add the component as a module.
