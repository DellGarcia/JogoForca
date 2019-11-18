package statics;

import java.util.ArrayList;
import java.util.Arrays;

public class Sound {
	
	// My Songs
	public final static String feelGoodInc = "assets/msc/FeelGoodInc.wav";
	
	private final static String caminho = "assets/msc/anime-soundtracks/";
	private final static String musicas[] = {
		caminho + "espada-theme.wav",
		caminho + "fullmetal-soundtrack.wav",
		caminho + "kira-theme.wav",
		caminho + "l-theme.wav",
		caminho + "neverland-soundtrack.wav",
		caminho + "pain-theme.wav",
		caminho + "zanguetsu-theme.wav",
		caminho + "zaraki-theme.wav",
	};
	
	public static final ArrayList<String> playlist = new ArrayList<String>(Arrays.asList(musicas));

}
