package delegate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DelegateTest {
 
	public static void main(String[] args) throws InterruptedException {

		// 放哨者
		Notifier goodNotifier = new GoodNotifier();
		
		PlayingGameListener playingGameListener = new PlayingGameListener();
		
		goodNotifier.addListener(playingGameListener, "stopPlayingGame1", new Date());
		
		TimeUnit.SECONDS.sleep(2);
		
		goodNotifier.notifyX();
		
	}

}
