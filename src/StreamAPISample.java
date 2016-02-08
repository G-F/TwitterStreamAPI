import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class StreamAPISample {

	private static String CONSUMER_KEY = "***";
	private static String CONSUMER_SECRET = "***";
	private static String ACCESS_TOKEN_SECRET ="***";
	private static String ACCESS_TOKEN = "***";

	static class StatusListenerImple implements StatusListener {

		@Override
		public void onException(Exception e) {
			System.out.println("onException");
			e.printStackTrace();
		}

		@Override
		public void onDeletionNotice(StatusDeletionNotice notice) {
			System.out.println("onDeletionNotice");
		}

		@Override
		public void onScrubGeo(long lat, long lng) {
			System.out.println("onScrubGeo.(" + lat + ", " + lng + ")");
		}

		@Override
		public void onStallWarning(StallWarning warning) {
			System.out.println("onStallWarning");
		}

		@Override
		public void onStatus(Status status) {
            System.out.println("Status: " + status.getText());

		}

		@Override
		public void onTrackLimitationNotice(int notice) {
			System.out.println("onTrackLimitationNotice");
		}

	}

	public static void main(String[] args) {
		Configuration configuration = new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY)
				.setOAuthConsumerSecret(CONSUMER_SECRET).setOAuthAccessToken(ACCESS_TOKEN)
				.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET).build();

		TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
		twitterStream.addListener(new StatusListenerImple());

		twitterStream.sample();

	}

}
