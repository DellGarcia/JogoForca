package telas;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Jogo;
import backend.Radio;
import backend.Som;
import frontend.LabelHTML;
import frontend.MyButton;
import frontend.MyLabel;
import frontend.MyPanel;
import model.Palavra;
import backend.RegistrarFont;
import statics.Alfabeto;
import statics.Cores;
import statics.Images;
import statics.PlanetFont;
import statics.Sound;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame implements KeyListener{
	
	// JPanel
	private JPanel tela;

	// JButton
	private JButton[] btnLetras = new JButton[26];
	
	// JLabel
	private JLabel lblForca;
	private JLabel lblLetras[];
	private JLabel lblCategoria;
	
	// Corpo do Bonequinho
	private JLabel lblCorpo[];
	
	// Toolkit
	public Toolkit tk = Toolkit.getDefaultToolkit();
	public Dimension d = tk.getScreenSize();
	
    // Jogo
    private Jogo game;
    private int indexQuestao;
    private Palavra palavraAtual;
    private int tentativaAtual;
    private int letrasReveladas;

    // Font
    private Font myFont;
    
    // Musica 
 	protected static Som objSom;
 	protected static String musicaFundo;
 	
 	// TelaPrincipal 
 	private static TelaPrincipal INSTANCE;
 	
 	// Construtor
	private TelaPrincipal() {
		//Configurações do JFrame
		setTitle("Jogo da Forca");
		setSize(d.width, d.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
				
		init();
		
		tela.add(lblForca);
		tela.add(lblCategoria);
		
		setContentPane(tela);
		setVisible(true);
	}
	
	public void init() {

		tela = new MyPanel(Cores.corCinzaClaro);
		
		new Thread(new Radio(Sound.playlist)).start();
		
		myFont = RegistrarFont.minhaFont(PlanetFont.planetncompact, "50");
		
		game = new Jogo();
		indexQuestao = 0;
		
		iniciarJogo();
		
	}
	
	private void iniciarJogo() {
		tentativaAtual = 0;
		letrasReveladas = 0;
		
		// Boneco
		colocarBoneco();
		
		// Palavra
		colocarPalavra();
		
		// Teclado
		colocarTeclado();
	}
	
	// Boneco
	private void colocarBoneco() {
		int deslocamentoH = 230, deslocamentoV = 14;
		Point coordenadas[] = {
			new Point(d.width/2 - 1280/2 + deslocamentoH+3, 100),
			new Point(d.width/2 - 1280/2 + deslocamentoH-6, 160 - deslocamentoV),
			new Point(d.width/2 - 1280/2 + deslocamentoH-10, 154 - deslocamentoV),
			new Point(d.width/2 - 1280/2 + deslocamentoH+13, 154 - deslocamentoV),
			new Point(d.width/2 - 1280/2 + deslocamentoH-10, 200 - deslocamentoV),
			new Point(d.width/2 - 1280/2 + deslocamentoH+12, 202 - deslocamentoV),
			new Point(d.width/2 - 1280/2 + deslocamentoH+1, 98)
		};
		
		// Configurando as partes do boneco
		lblCorpo = new JLabel[7];
		for(int i = 0; i < 7; i++) {
			lblCorpo[i] = new MyLabel(50, 50, new ImageIcon(Images.corpo[i]));
			lblCorpo[i].setLocation(coordenadas[i]);
			lblCorpo[i].setVisible(false);
			tela.add(lblCorpo[i]);
		}
	}
	
	// Palavra
	private void colocarPalavra() {
		lblForca = new MyLabel(265, 535, new ImageIcon(Images.forca));
		lblForca.setLocation(d.width/2 - 1280/2, 40);
		
		palavraAtual = game.getPalavras().get(indexQuestao);
		
		// Categoria da Palavra
		lblCategoria = new MyLabel(
				800, 100,
				LabelHTML.html(palavraAtual.getCategoria()),
				myFont, Cores.corGhostWhite);
		lblCategoria.setLocation(d.width/2 - 1280/2 + 300, 40);
		
		// Letras da Palavra
		lblLetras = new JLabel[palavraAtual.getPalavra().length()];

		int reguladorHorizontal = 100;
		for(int i = 0; i < palavraAtual.getPalavra().length(); i++) {
			
			lblLetras[i] = new MyLabel(
					70, 60,
					palavraAtual.getPalavra().charAt(i)+"",
					myFont, Cores.corCinzaClaro);
			lblLetras[i].setLocation(d.width/2 - 1280/2 + reguladorHorizontal, 515);
			
			if(palavraAtual.getPalavra().charAt(i) != ' ') {
				lblLetras[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Cores.corCinza));
			}
			reguladorHorizontal += 80;
			tela.add(lblLetras[i]);
		}
	}
	
	// Teclado
	private void colocarTeclado() {
		int x = d.width/2 - 1280/2, y = d.height - 250;
		for(int i = 0; i < 26; i++) {
			btnLetras[i] = new MyButton(
				x, y,
				80, 80,
				Cores.corCinza, Cores.corGhostWhite,
				myFont, Alfabeto.alfabeto[i], Cores.corPreta, 3,
				Cores.corRoxa, Cores.corGhostWhite
			);
			int r = i;
			btnLetras[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					conferirString(Alfabeto.alfabeto[r]);
					btnLetras[r].setVisible(false);
				}
			});
			btnLetras[i].setBorder(null);
			tela.add(btnLetras[i]);
			x += 100;
			if((i+1) % 13 == 0) {
				x = d.width/2 - 1280/2;
				y += 100;
			}
			btnLetras[i].addKeyListener(this);
		}	
	}
	
	// Reset
	private void atualizaForca() {
		tela.remove(lblCategoria);
		for(int i = 0; i < 7; i++) {
			tela.remove(lblCorpo[i]);
		}
		for(int i = 0; i < 26; i++) {
			tela.remove(btnLetras[i]);
		}
		for(int i = 0; i < palavraAtual.getPalavra().length(); i++) {
			tela.remove(lblLetras[i]);
		}
		tela.repaint();
	}
	
	// Logica
	private void conferirString(String letra) {
		boolean acertou = false;
		for(int i = 0; i < palavraAtual.getPalavra().length(); i++) {
			if(lblLetras[i].getText().equalsIgnoreCase(letra)) {
				lblLetras[i].setForeground(Cores.corGhostWhite);
				letrasReveladas++;
				acertou = true;
			}
		}
		
		if(letrasReveladas == palavraAtual.getPalavra().replaceAll(" ", "").length()) {
			if(indexQuestao + 1 < game.getPalavras().size()) {
				indexQuestao++;
			}else {
				game = new Jogo();
				indexQuestao = 0;
			}
			atualizaForca();
			iniciarJogo();
		}
		
		if(!acertou) {
			lblCorpo[tentativaAtual].setVisible(true);
			tentativaAtual++;
			if(tentativaAtual >= 7) {
				indexQuestao++;
				atualizaForca();
				iniciarJogo();
			}
		}
	}
	
	// KeyBoard
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 26; i++) { 
			if(e.getKeyCode() == Alfabeto.valorTeclado[i]) {
				btnLetras[i].setBackground(Cores.corRoxa);
				btnLetras[i].setForeground(Cores.corGhostWhite);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 26; i++) { 
			if(e.getKeyCode() == Alfabeto.valorTeclado[i]) {
				conferirString(Alfabeto.alfabeto[i]);
				btnLetras[i].setBackground(Cores.corCinza);
				btnLetras[i].setForeground(Cores.corGhostWhite);
				btnLetras[i].setVisible(false);
			}
		}
	}
	
	// INSTANCE
	public static synchronized void setInstance() {
		INSTANCE.dispose();
		INSTANCE = null;
	}
	
	public static synchronized void restart() {
		// Pega a antiga instancia, cria uma nova e fecha a antiga
		TelaPrincipal OLDINSTANCE = INSTANCE;
		INSTANCE = new TelaPrincipal();
		OLDINSTANCE.dispose();
	}
	
	
	public static synchronized TelaPrincipal getInstance() {
		// Se não haver uma instancia, cria uma
		if (INSTANCE == null) {
			INSTANCE = new TelaPrincipal();
		}
		
		return INSTANCE;
	}

	
}
