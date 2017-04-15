package delegate;

import java.util.Date;

/**
 * 
 * 玩游戏的同学类<br>
 * 委托放哨同学类对自己提醒<br>
 * 
 * @author QZidane
 *
 */
public class PlayingGameListener {

	public PlayingGameListener() {
		System.out.println("我正在玩游戏，开始时间=" + new Date());
	}
	
	public void stopPlayingGame(Date date){
		System.out.println("老师来了，停止玩游戏，结束时间="+date);
	}

}
