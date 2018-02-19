# MultiModuleProject

An app with multiple flavors that uses multiple libraries and Google Cloud Endpoints(App Engine). It consist of four modules. A Java library that provides jokes, a Google Cloud Endpoints (GCE) project that serves those jokes, an Android Library containing an activity for displaying jokes, and an Android app that fetches jokes from the GCE module and passes them to the Android Library for display.

## Feature
* A java project that provides jokes.
* A Google Cloud Endpoints (GCE) project using Google App Engine as a server.
* An Android Library as a library containing an activity for displaying jokes.
* An Android app fetches jokes from the GCE module and passes them to the Android Library for display.
* Contain free and paid Flavor with multiple implementation,free flavor contain ads,while paid flavor doesn't have.
* Interstitial Ad.
* InstrumentationTest (AsyncTask with App Engine).
## Screen  
### Free
<img src="../master/read_me_pictures/free.gif" alt="alt text" width="348" height="611">

### Paid
<img src="../master/read_me_pictures/paid.gif" alt="alt text" width="348" height="611">

## Libraries  
* Google App Engine
* Google Play Service(Ads)
