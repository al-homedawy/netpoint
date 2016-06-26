# NETPoint

NETPoint is a light-weight network communication protocol for the Android operating system. This network communication wrapper can save a developer much time and effort for elementary to moderate networking calls. 

## Usage

### Importing the library

#### Maven

You can import the library using Maven.

```
repositories {
  maven {
    url 'https://dl.bintray.com/hussain-al/maven/'
  }
}

...

dependencies {
    compile 'com.example.hussain_pc.netpoint.NETPoint:netpoint:0.0.1'
}
```

#### Add permissions to your AndroidManifest file

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Example

```public 
class testingClass implements NETCallback {
    public testingClass() {

    }

    @Override
    public void RequestComplete(NETUrl httpUrl, String receivedData) {
        // TODO
    }

    @Override
    public void RequestComplete(NETUrl httpUrl, JSONObject jsonData) {
        // TODO
    }
}
```

```//Example
// Initialize our NETPoint class
NETPoint netPoint = new NETPoint();

// Create our end-points
NETUrl netUrl = new NETUrl("http://www.google.com", true);
NETUrl netUrl2 = new NETUrl("http://www.facebook.com", false);
NETUrl netUrl3 = new NETUrl("http://www.al-homedawy.com", false);
NETUrl netUrl4 = new NETUrl("http://maps.googleapis.com/maps/api/geocode/json", true);
netUrl4.addParameter("latlng", "0");
netUrl4.addParameter("sensor", "false");

// Poll a single end-point 
netPoint.pollSingleEndPoint(netUrl4, new testingClass());

// Add our end-points to our net-point class
netPoint.addEndPoint(netUrl, new testingClass());
netPoint.addEndPoint(netUrl2, new testingClass());
netPoint.addEndPoint(netUrl3, new testingClass());
netPoint.addEndPoint(netUrl4, new testingClass());
netPoint.pollAllEndPoints();
```

### NETCallback

The ```NETCallback``` interface must be implemented in a class that handles your client-sided response. The ```NETCallback``` interface contains the ```RequestComplete(...)``` function that must be overridden. The ```RequestComplete(...)``` function is an overloaded function with two implementations. The former triggers your callback with raw data from the end-point while the latter triggers your callback with a JSon format of your data. You only need to override one of the two implementations.

### NETUrl

The ```NETUrl``` class defines your server-sided endpoint. The constructor must be initialized with a http formatted URL of the server-sided end-point followed by if end-point outputs data in a JSon format or not (true or false). The option to add parameters to your end-point is available by calling the ```addParameter(...)``` function. The ```addParameter(...)``` function is an overloaded function:

```public void addParameter(String key)``` and ```public void addParameter(String key, Object value)``` respectively.

There is no limit to how many parameters you can add to your end-point. Ensure that prior to polling your end-point you update the parameters by recalling ```public void addParameter(String key, Object value)```. You also have the ability to clear all end-point parameters with a call to ```clearParameters()```.

### NETPoint

The ```NETPoint``` class is the main class in this library that coordinates all networking calls. After initializing your end-points, you can add them into an instance of ```NETPoint``` by calling ```public boolean addEndPoint(NETUrl httpUrl, NETCallback callback)```. The first parameter of ```addEndPoint(...)``` is your ```NETUrl``` instance followed by a reference to your ```NETCallback``` sub-class. You can then call ```pollAllEndPoints()``` to poll each end-point and invoke all your callbacks synchronously. You can alternatively make a call to the overloaded function ```pollSingleEndPoint(...)```:

```public void pollSingleEndPoint(NETUrl httpUrl)``` and ```public void pollSingleEndPoint(NETUrl httpUrl, NETCallback callback)```.

The former will only poll the end-point if the 'NETUrl' has already been added to your 'NETPoint' class. The latter will poll your end-point regardless of whether or not it was already added into the 'NETPoint' class.

## Contributing

Bug reports and pull requests are welcome on GitHub at [hussain-alhomedawy](https://github.com/hussain-alhomedawy/NETPoint).

## License

The repository is available as open source under the terms of the [MIT](https://opensource.org/licenses/MIT) License

