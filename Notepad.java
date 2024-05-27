
//import the packages for using the classes in them into the program
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;

/**
 *A PUBLIC CLASS FOR NOTEPAD.JAVA
 */
public class Notepad extends JFrame{
	//for using the methods in these classes
	public Actions actions = new Actions(this);
	public Center center = new Center(this);
	
	//for using undo & redo
	UndoManager undo = new UndoManager();
	UndoAction undoAction = new UndoAction();
	RedoAction redoAction = new RedoAction();
	
	//declaration of the private variables used in the program
	//create the text area
	private JTextArea textArea;
	//create the Menu Bar that contains the JMenu "filE, ediT, formaT, helP"
	private JMenuBar Menubar;
	//Create the menu that contains the items
	private JMenu filE, ediT, vieW, formaT, helP;
	//Create the menu items
	private JMenuItem neW, opeN, savE, saveAS, prinT, exiT, fonT, abouT,
	cuT, copY, pastE, selectALL, finD, findNexT;
	private JCheckBoxMenuItem lineWraP;
	//Create the Tool Bar that contains the JButton
	private JToolBar toolBar;
	//Create the buttons
	private JButton newButton, openButton, saveButton, saveAsButton, printButton, 
	undoButton, redoButton, cutButton, copyButton, pasteButton, findButton,
	fontButton, aboutButton;
	//Create Scroll pane (JScrollPane) for the JTextArea
	private JScrollPane scrollpane;
	
	//for using lineWrap & textArea @Actions.java
	public JCheckBoxMenuItem getLineWrap(){
		return lineWraP;
	}
	public JTextArea getTextArea(){
		return textArea;
	}
	//Constructor of Notepad
	public Notepad(){
		//set the title for Notepad and set the size for it.
		setTitle("Untitled - QNote");
		setSize(1280,720);
		
		//get the graphical user interface components display area
		Container cp = getContentPane();
		/**
		 *adding the text area,
		 *adding the tool bar &
		 *adding the scroll pane to the container
		 */
		cp.add(textArea = new JTextArea());
		cp.add("North", toolBar = new JToolBar("Tool Bar"));
		cp.add(scrollpane = new JScrollPane(textArea)); 
		JScrollPane scrollpane = new JScrollPane (textArea, 
   		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		cp.add(scrollpane);
		cp.setVisible (true);
    

		//sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
		 //Create JFrame with title ( Set JTextArea background color )
 		//-JFrame frame=new JFrame("Set JTextArea background color");

		//Set color base on RGB
		//You can get RGB value for your color at "Color picker" at above
		//R=255
		//G=0
		//B=0
		Color color=new Color(250,229,220);
		Color color2=new Color(73,1,110);

		//Set JTextArea background color to color that you choose
		textArea.setBackground(color);
		toolBar.setBackground(color2);
		
		//Add JTextArea into JFrame
		cp.add(textArea);

		//Set default close operation for JFrame
		//cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set JFrame size
		cp.setSize(1366,768);

		//Make JFrame visible
		cp.setVisible(true);
		//sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss

		//for setting the menu bar
		setJMenuBar(Menubar= new JMenuBar());
		//adding file, edit, view, format, help to the menu bar
		Menubar.add(filE   = new JMenu("File"));
		Menubar.add(ediT   = new JMenu("Edit"));
		Menubar.add(formaT = new JMenu("Format"));
		Menubar.add(helP   = new JMenu("Help"));
		
		/**
		 *adding neW, opeN, savE, saveAS, prinT & exiT to the filE Menu,
		 *adding a small image icon to the menu item &
		 *adding separator between the menu item
		 */
		filE.add(neW    = new JMenuItem("New", new ImageIcon("images/new.gif")));
		filE.add(opeN   = new JMenuItem("Open", new ImageIcon("images/open.gif")));
		filE.add(savE   = new JMenuItem("Save", new ImageIcon("images/save.gif")));
		filE.add(saveAS = new JMenuItem("Save As", new ImageIcon("images/saveAs.gif")));
		filE.add(prinT  = new JMenuItem("Print", new ImageIcon("images/print.gif")));
		filE.add(exiT   = new JMenuItem("Exit", new ImageIcon("images/exit.gif")));
		filE.insertSeparator(4);
		filE.insertSeparator(6);
		
		/**
		 *adding undO, redO, cuT, copY, pastE, finD, findNexT & selectALL to the ediT Menu,
		 *adding a small image icon to the menu item &
		 *adding separator between the menu item
		 */
		ediT.add(undoAction);
		ediT.add(redoAction);
		ediT.add(cuT  = new JMenuItem("Cut",  new ImageIcon("images/cut.gif")));
		ediT.add(copY = new JMenuItem("Copy", new ImageIcon("images/copy.gif")));
		ediT.add(pastE= new JMenuItem("Paste",new ImageIcon("images/paste.gif")));
		ediT.add(finD = new JMenuItem("Find", new ImageIcon("images/find.gif")));
		ediT.add(findNexT = new JMenuItem("Find Next"));
		ediT.add(selectALL= new JMenuItem("Select All"));
		ediT.insertSeparator(2);
		ediT.insertSeparator(6);
		ediT.insertSeparator(9);
		
		/**
		 *adding lineWraP & fonT to the formaT Menu,
		 *adding abouT to the helP Menu & 
		 *adding a samll image icon to the menu item
		 */
		formaT.add(lineWraP = new JCheckBoxMenuItem("Line Wrap"));
		formaT.add(fonT = new JMenuItem("Font", new ImageIcon("images/font.gif")));
		helP.add(abouT = new JMenuItem("About QNote", new ImageIcon("images/about.gif")));
		
		/**
		 *allowing the file   menu to be selected by pressing ALT + F
		 *allowing the edit   menu to be selected by pressing ALT + E
		 *allowing the view   menu to be selected by pressing ALT + V
		 *allowing the format menu to be selected by pressing ALT + O
		 *allowing the help   menu to be selected by pressing ALT + H
		 */
		filE.setMnemonic('f');
		ediT.setMnemonic('e');
		formaT.setMnemonic('o');
		helP.setMnemonic('h');
		
		/**
		 *allowing the neW       menu item to be selected by pressing ALT + N
		 *allowing the opeN      menu item to be selected by pressing ALT + O
		 *allowing the savE      menu item to be selected by pressing ALT + S
		 *allowing the prinT     menu item to be selected by pressing ALT + P
		 *allowing the exiT      menu item to be selected by pressing ALT + F4
		 *allowing the cuT       menu item to be selected by pressing ALT + X
		 *allowing the copY      menu item to be selected by pressing ALT + C
		 *allowing the pastE     menu item to be selected by pressing ALT + V
		 *allowing the finD      menu item to be selected by pressing ALT + F
		 *allowing the findNexT  menu item to be selected by pressing ALT + F3
		 *allowing the selectAll menu item to be selected by pressing ALT + A
		 */
		neW.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		opeN.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		savE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		prinT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		exiT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
		cuT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		copY.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		pastE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		finD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		findNexT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
		selectALL.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		/**
		 *adding newButton, openButton, saveButton, saveAsButton, printButton, undoButton,
		 *redoButton, cutButton, copyButton, pasteButton, fontButton & aboutButton to the tool bar,
		 *adding a small image icon to the menu item &
		 *adding separator between the button
		 */
		toolBar.add(newButton   = new JButton(new ImageIcon("images/new.gif")));
		toolBar.add(openButton  = new JButton(new ImageIcon("images/open.gif")));
		toolBar.add(saveButton  = new JButton(new ImageIcon("images/save.gif")));
		toolBar.add(saveAsButton= new JButton(new ImageIcon("images/saveAs.gif")));
		toolBar.add(printButton = new JButton(new ImageIcon("images/print.gif")));
		toolBar.addSeparator();
		toolBar.add(undoAction);
		toolBar.add(redoAction);
		toolBar.addSeparator();
		toolBar.add(cutButton   = new JButton(new ImageIcon("images/cut.gif")));
		toolBar.add(copyButton  = new JButton(new ImageIcon("images/copy.gif")));
		toolBar.add(pasteButton = new JButton(new ImageIcon("images/paste.gif")));
		toolBar.add(findButton  = new JButton(new ImageIcon("images/find.gif")));
		toolBar.addSeparator();
		toolBar.add(fontButton  = new JButton(new ImageIcon("images/font.gif")));
		toolBar.add(aboutButton = new JButton(new ImageIcon("images/about.gif")));
		
		//adding a tool tip text to the button for descriping the image icon.
		newButton.setToolTipText("New");
		openButton.setToolTipText("Open");
		saveButton.setToolTipText("Save");
		saveAsButton.setToolTipText("Save As");
		printButton.setToolTipText("Print");
		cutButton.setToolTipText("Cut");
		copyButton.setToolTipText("Copy");
		pasteButton.setToolTipText("Paste");
		findButton.setToolTipText("Find");
		fontButton.setToolTipText("Font");
		aboutButton.setToolTipText("About QNote");
		
		/**
		 *setting the default close operation to false &
		 *using own action (exiT action @Actions.java)
		 */
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				actions.exiT();
			}
		});
		/**
		 *adding action listener for menu item: neW, opeN, savE, saveAS, prinT, exiT,
		 *redO, undO, copY, cuT, pastE, finD, findNexT, selectALL, lineWraP, fonT & abouT
		 *the actions was written @Actions.java
		 */
		neW.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.neW();
			}
		});
		opeN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.opeN();
			}
		});
		savE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.savE();
			}
		});
		saveAS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.saveAs();
			}
		});
		prinT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.prinT();
			}
		});
		exiT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.exiT();
			}
		});
		cuT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.cuT();
			}
		});
		copY.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.copY();
			}
		});
		pastE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.pastE();
			}
		});
		selectALL.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.selectALL();
			}
		});
		finD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.finD();
			}
		});
		findNexT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.findNexT();
			}
		});
		lineWraP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.lineWraP();
			}
		});
		fonT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.fonT();
			}
		});
		abouT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.abouT();
			}
		});
		
		/**
		 *adding action listener for the button in the tool bar: newButton, openButton,
		 *saveButton, saveAsButton, printButton, redoButton, undoButton, copyButton,
		 *cutButton, pasteButton, findButton, selectALL, lineWraP, fontButton & aboutButton
		 *the actions was written @Actions.java
		 */
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.neW();
			}
		});
		openButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.opeN();
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.savE();
			}
		});
		saveAsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.saveAs();
			}
		});
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.prinT();
			}
		});
		cutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.cuT();
			}
		});
		copyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.copY();
			}
		});
		pasteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.pastE();
			}
		});
		findButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.finD();
			}
		});
		fontButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.fonT();
			}
		});
		aboutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actions.abouT();
			}
		});			
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener(){
			public void undoableEditHappened(UndoableEditEvent e){
				//Remember the edit and update the menus
				undo.addEdit(e.getEdit());
				undoAction.update();
				redoAction.update();
			}
		}); 
		
		/**
		 *Setting the Line Wrap & Wrap Style Word features are true 
		 */
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		/**
		 *for making the program at the center, 
		 *@see Center.java 
		 */
		center.nCenter();
		show();
	}
	//Main Method
	public static void main(String[] args){
		new Notepad();
		
	}
	//UNDO AND REDOACTION CLASSES
	class UndoAction extends AbstractAction{
		public UndoAction(){
			super("Undo", new ImageIcon("images/undo.gif"));
			setEnabled(false);
		}
		public void actionPerformed(ActionEvent e){
			try{
				undo.undo();
			}
			catch (CannotUndoException ex){
				System.out.println("Unable to undo: " + ex);
				ex.printStackTrace();
			}
			update();
			redoAction.update();
		}
		protected void update(){
			if(undo.canUndo()){
				setEnabled(true);
				putValue("Undo", undo.getUndoPresentationName());
			}
			else{
				setEnabled(false);
				putValue(Action.NAME, "Undo");
			}
		}
	}
	class RedoAction extends AbstractAction{
		public RedoAction(){
			super("Redo", new ImageIcon("images/redo.gif"));
			setEnabled(false);
		}
		public void actionPerformed(ActionEvent e){
			try{
				undo.redo();
			}
			catch (CannotRedoException ex){
				System.out.println("Unable to redo: " + ex);
				ex.printStackTrace();
			}
			update();
			undoAction.update();
		}
		protected void update(){
			if(undo.canRedo()){
				setEnabled(true);
				putValue("Redo", undo.getRedoPresentationName());
			}
			else{
				setEnabled(false);
				putValue(Action.NAME, "Redo");
			}
		}
	}
}			