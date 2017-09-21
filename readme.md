## Tweet getter 

You can parse a hashtag's sent tweets and write to firebase database.

#### application.properties
**tweet.limit**  = Tweet count of to be parse each time

**tweet.hastag** = Keyword to search for (e.g #dfesk)

**firebase.url** = Firebase database url (e.g https://blabal.firebaseio.com/tweets.json)

**firebase.serviceAccountJsonFileName** = The name of the service account file required to write to Firebase Database
> Important: Your service account file must be in the resource folder

#### Build Jar

    mvn clean package

#### Run
    java -jar target/java-tweet-getter-*.jar

### Credit

[https://github.com/Jefferson-Henrique/GetOldTweets-java](https://github.com/Jefferson-Henrique/GetOldTweets-java)