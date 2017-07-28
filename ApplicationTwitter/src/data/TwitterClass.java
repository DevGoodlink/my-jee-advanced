/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author YASSALIE
 */
public class TwitterClass {

    public static ConfigurationBuilder confbuilder;

    static {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("twitter4j.properties");
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        confbuilder = new ConfigurationBuilder();
        confbuilder.setOAuthAccessToken(prop.getProperty("oauth.accessToken")) 
                .setOAuthAccessTokenSecret(prop.getProperty("oauth.accessTokenSecret"))
                .setOAuthConsumerKey(prop.getProperty("oauth.consumerKey"))
                .setOAuthConsumerSecret(prop.getProperty("oauth.consumerSecret"));
    }

    public static List<String> getAllTweetList(String queryString) {
        Twitter twitter = new TwitterFactory(confbuilder.build()).getInstance();
        try {
            Query query = new Query(queryString);

            QueryResult result;
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            List<String>  listTweets= new ArrayList<>();
            for (Status tweet : tweets) {
                String user = "<html><b><u>@" + tweet.getUser().getScreenName()+"</u></b><p><font color=blue>";
                String text = tweet.getText()+"</font></p></html>";
                listTweets.add(user+text);
            }
            return listTweets;
           
        } catch (TwitterException te) {
            te.printStackTrace();
            
            return null;
        }

    }

}
