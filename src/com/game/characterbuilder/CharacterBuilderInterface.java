/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.characterbuilder;

/**
 *
 * @author 韩信
 */
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CharacterBuilderInterface extends JFrame{
	
	private String CharacterInformation="Class: \n" + "HitPoint: \n" + "ArmorClass: \n" + "Movement: \n" + "Melee Attack bonus: \n" + "Range Attack bonus: \n";
	private String ClassFighter="Alignment: Any \n" + "Hit Die: d10 \n" + "Class Skills: \n" + "The fighter’s class skills (and the key ability " + "for each skill) are Climb (Str), Craft (Int), Handle " + " Animal (Cha), Intimidate (Cha), Jump (Str), " + "Ride (Dex), and Swim (Str). \n";
	private String[] Classes=new String[]{"Fighter"};
	private int[] modifier = {-5,-4,-4,-3,-3,-2,-2,-1,-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17};
	private int NumberOfAviliblePoints;
	private int StrPoints;
	private int CurrentLevel;
	private int CurrentExp;
	private int NeedExp;
	
	public CharacterBuilderInterface() {

        initUI();
    }
	
	public String getCharacterInformation() {
		return CharacterInformation;
	}

	public void setCharacterInformation(String characterInformation) {
		CharacterInformation = characterInformation;
	}

	public String getClassFighter() {
		return ClassFighter;
	}

	public void setClassFighter(String classFighter) {
		ClassFighter = classFighter;
	}

	public String[] getClasses() {
		return Classes;
	}

	public void setClasses(String[] classes) {
		Classes = classes;
	}

	public int[] getModifier() {
		return modifier;
	}

	public void setModifier(int[] modifier) {
		this.modifier = modifier;
	}

	public int getNumberOfAviliblePoints() {
		return NumberOfAviliblePoints;
	}

	public void setNumberOfAviliblePoints(int numberOfAviliblePoints) {
		NumberOfAviliblePoints = numberOfAviliblePoints;
	}

	public int getStrPoints() {
		return StrPoints;
	}

	public void setStrPoints(int strPoints) {
		StrPoints = strPoints;
	}

	public int getCurrentLevel() {
		return CurrentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		CurrentLevel = currentLevel;
	}

	public int getCurrentExp() {
		return CurrentExp;
	}

	public void setCurrentExp(int currentExp) {
		CurrentExp = currentExp;
	}

	public int getNeedExp() {
		return NeedExp;
	}

	public void setNeedExp(int needExp) {
		NeedExp = needExp;
	}

	public String calculateAbilityPoints(int abilitypoints){
		String results="";
		if(modifier[abilitypoints]<0){
			results=Integer.toString(abilitypoints) + " (" + Integer.toString(modifier[abilitypoints]) + ")";
		}else{
			results=Integer.toString(abilitypoints) +" (+ " + Integer.toString(modifier[abilitypoints]) + ")";
		}
		return results;
	}
	
	public void initUI() {
		//Initialize points value
		NumberOfAviliblePoints=32;
		StrPoints=8;
		
		
		JPanel basicPanel = new JPanel();
		
		JPanel AbilityPointPanel = new JPanel();
		AbilityPointPanel.setLayout(new BoxLayout(AbilityPointPanel, BoxLayout.Y_AXIS));
		
		JPanel TopofAbilityPointPanel = new JPanel();
		JPanel BelowofAbilityPointPanel = new JPanel();
		JPanel LableOfAbilityPoint = new JPanel();
		LableOfAbilityPoint.setLayout(new BoxLayout(LableOfAbilityPoint, BoxLayout.Y_AXIS));
		JPanel SubOfAbilityPoint = new JPanel();
		SubOfAbilityPoint.setLayout(new BoxLayout(SubOfAbilityPoint, BoxLayout.Y_AXIS));
		JPanel TextFieldOfAbilityPoint = new JPanel();
		TextFieldOfAbilityPoint.setLayout(new BoxLayout(TextFieldOfAbilityPoint, BoxLayout.Y_AXIS));
		JPanel PlusOfAbilityPoint = new JPanel();
		PlusOfAbilityPoint.setLayout(new BoxLayout(PlusOfAbilityPoint, BoxLayout.Y_AXIS));
		
		JPanel InformationPanel = new JPanel();
		InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
		JPanel NamePanel = new JPanel();
		JPanel LevelPanel = new JPanel();
		JPanel ExpPanel = new JPanel();
		
		JPanel ClassInformationPanel = new JPanel();
		ClassInformationPanel.setLayout(new BorderLayout());
		ClassInformationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JScrollPane scroll = new JScrollPane();
		
		JPanel ClassChoicePanel = new JPanel();
		ClassChoicePanel.setLayout(new BorderLayout());
		ClassInformationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JScrollPane ClassChoiceScroll = new JScrollPane();
		JList<?> ClassList = new JList<Object>(Classes);
		
		//BelowofAbilityPointPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//BelowofAbilityPointPanel.setLayout(new GridLayout(0, 4, 5, 5));
		
		//Initialize Label components
		JLabel AvailablePointsJLabel = new JLabel("AvailablePoints"); 
		JLabel STRLable = new JLabel("STRENGTH (STR)");
		JLabel DEXLable = new JLabel("DEXTERITY (DEX)");
		JLabel CONLable = new JLabel("CONSTITUTION (CON)");
		JLabel INTLable = new JLabel("INTELLIGENCE (INT)");
		JLabel WISLable = new JLabel("WISDOM (WIS)");
		JLabel CHALable = new JLabel("CHARISMA (CHA)");
		JLabel Name = new JLabel("Name:");
		JLabel Level = new JLabel("Level:");
		JLabel ExpLabel = new JLabel("EXP:");
		
		//Initialize Button components
		JButton STRPlus = new JButton("+");
		JButton STRSub = new JButton("-");
		JButton DEXPlus = new JButton("+");
		JButton DEXSub = new JButton("-");
		JButton CONPlus = new JButton("+");
		JButton CONSub = new JButton("-");
		JButton INTPlus = new JButton("+");
		JButton INTSub = new JButton("-");
		JButton WISPlus = new JButton("+");
		JButton WISSub = new JButton("-");
		JButton CHAPlus = new JButton("+");
		JButton CHASub = new JButton("-");
		JButton SavePoint = new JButton("SavePoint");
		JButton ResetPoint = new JButton("ResetPoint");
		JButton SaveCharacter = new JButton("save");
		SaveCharacter.setLayout(null);
		SaveCharacter.setSize(100, 200);
		JButton LoadCharacter = new JButton("load");
		JButton ShowInformation = new JButton("ShowInformation");
		
		//Initialize TextField components
		JTextField AvailablePoints = new JTextField(Integer.toString(NumberOfAviliblePoints));
		JTextField STRField = new JTextField(calculateAbilityPoints(StrPoints));
		JTextField DEXField = new JTextField("0 + 0");
		JTextField CONField = new JTextField("0 + 0");
		JTextField INTField = new JTextField("0 + 0");
		JTextField WISField = new JTextField("0 + 0");
		JTextField CHAField = new JTextField("0 + 0");
		JTextField NameField = new JTextField("please input name");
		JTextField LevelField = new JTextField(Integer.toString(CurrentLevel));
		JTextField ExpField = new JTextField(Integer.toString(CurrentExp) + "/" + Integer.toString(NeedExp));
		JTextArea InformationArea = new JTextArea(CharacterInformation);
		InformationArea.setEditable(false);
		AvailablePoints.setEditable(false);
		STRField.setEditable(false);
		DEXField.setEditable(false);
		CONField.setEditable(false);
		INTField.setEditable(false);
		WISField.setEditable(false);
		CHAField.setEditable(false);
		LevelField.setEditable(false);
		ExpField.setEditable(false);
		
		JTextArea ClassDiscreption = new JTextArea(ClassFighter);
		ClassDiscreption.setEditable(false);
		ClassDiscreption.setLineWrap(true);
		ClassDiscreption.setWrapStyleWord(true);
		ClassDiscreption.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		
		//add components to ClassInformationPanel
		ClassChoiceScroll.getViewport().add(ClassList);
		ClassChoiceScroll.setPreferredSize(new Dimension(100, 200));
			
		//add components to ClassSelectPanel
		scroll.getViewport().add(ClassDiscreption);
		scroll.setPreferredSize(new Dimension(200, 200));
		
		//add components to AbilityPointPanel
		TopofAbilityPointPanel.add(AvailablePointsJLabel);
		TopofAbilityPointPanel.add(AvailablePoints);

		/*BelowofAbilityPointPanel.add(STRLable);
		BelowofAbilityPointPanel.add(STRSub);
		BelowofAbilityPointPanel.add(STRField);
		BelowofAbilityPointPanel.add(STRPlus);
		BelowofAbilityPointPanel.add(DEXLable);
		BelowofAbilityPointPanel.add(DEXSub);
		BelowofAbilityPointPanel.add(DEXField);
		BelowofAbilityPointPanel.add(DEXPlus);
		BelowofAbilityPointPanel.add(CONLable);
		BelowofAbilityPointPanel.add(CONSub);
		BelowofAbilityPointPanel.add(CONField);
		BelowofAbilityPointPanel.add(CONPlus);
		BelowofAbilityPointPanel.add(INTLable);
		BelowofAbilityPointPanel.add(INTSub);
		BelowofAbilityPointPanel.add(INTField);
		BelowofAbilityPointPanel.add(INTPlus);
		BelowofAbilityPointPanel.add(WISLable);
		BelowofAbilityPointPanel.add(WISSub);
		BelowofAbilityPointPanel.add(WISField);
		BelowofAbilityPointPanel.add(WISPlus);
		BelowofAbilityPointPanel.add(CHALable);
		BelowofAbilityPointPanel.add(CHASub);
		BelowofAbilityPointPanel.add(CHAField);
		BelowofAbilityPointPanel.add(CHAPlus);;
		BelowofAbilityPointPanel.add(GapLable);
		BelowofAbilityPointPanel.add(SavePoint);
		BelowofAbilityPointPanel.add(GapLable);
		BelowofAbilityPointPanel.add(ResetPoint);*/
		
		LableOfAbilityPoint.add(STRLable);
		LableOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 13)));
		LableOfAbilityPoint.add(DEXLable);
		LableOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 13)));
		LableOfAbilityPoint.add(CONLable);
		LableOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 13)));
		LableOfAbilityPoint.add(INTLable);
		LableOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 13)));
		LableOfAbilityPoint.add(WISLable);
		LableOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 13)));
		LableOfAbilityPoint.add(CHALable);
		
		SubOfAbilityPoint.add(STRSub);
		SubOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		SubOfAbilityPoint.add(DEXSub);
		SubOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		SubOfAbilityPoint.add(CONSub);
		SubOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		SubOfAbilityPoint.add(INTSub);
		SubOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		SubOfAbilityPoint.add(WISSub);
		SubOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		SubOfAbilityPoint.add(CHASub);
		
		TextFieldOfAbilityPoint.add(STRField);
		TextFieldOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 9)));
		TextFieldOfAbilityPoint.add(DEXField);
		TextFieldOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 9)));
		TextFieldOfAbilityPoint.add(CONField);
		TextFieldOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 9)));
		TextFieldOfAbilityPoint.add(INTField);
		TextFieldOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 9)));
		TextFieldOfAbilityPoint.add(WISField);
		TextFieldOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 9)));
		TextFieldOfAbilityPoint.add(CHAField);
		
		PlusOfAbilityPoint.add(STRPlus);
		PlusOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		PlusOfAbilityPoint.add(DEXPlus);
		PlusOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		PlusOfAbilityPoint.add(CONPlus);
		PlusOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		PlusOfAbilityPoint.add(INTPlus);
		PlusOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		PlusOfAbilityPoint.add(WISPlus);
		PlusOfAbilityPoint.add(Box.createRigidArea(new Dimension(0, 3)));
		PlusOfAbilityPoint.add(CHAPlus);
		
		BelowofAbilityPointPanel.add(LableOfAbilityPoint);
		BelowofAbilityPointPanel.add(SubOfAbilityPoint);
		BelowofAbilityPointPanel.add(TextFieldOfAbilityPoint);
		BelowofAbilityPointPanel.add(PlusOfAbilityPoint);
		
		AbilityPointPanel.add(TopofAbilityPointPanel);
		AbilityPointPanel.add(BelowofAbilityPointPanel);
		AbilityPointPanel.add(ResetPoint);
		
		//add components to information panel
		NamePanel.add(Name);
		NamePanel.add(NameField);
		InformationPanel.add(NamePanel);
		InformationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		InformationPanel.add(InformationArea);
		InformationPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		LevelPanel.add(Level);
		LevelPanel.add(LevelField);
		ExpPanel.add(ExpLabel);
		ExpPanel.add(ExpField);
		InformationPanel.add(LevelPanel);
		InformationPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		InformationPanel.add(ExpPanel);
		InformationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		InformationPanel.add(ShowInformation);
		InformationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		InformationPanel.add(SaveCharacter);
		InformationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		InformationPanel.add(LoadCharacter);
		
		
		//add panels on BasicPanel
		basicPanel.add(ClassChoiceScroll);
		basicPanel.add(scroll);
		basicPanel.add(AbilityPointPanel);
		basicPanel.add(InformationPanel);
		basicPanel.setPreferredSize(new Dimension(800,400));
		add(basicPanel);
		
		setTitle("CharacterBuilder");
        setSize(900, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SwingUtilities.invokeLater(new Runnable() {

	            public void run() {

	            	CharacterBuilderInterface ex = new CharacterBuilderInterface();
	                ex.setVisible(true);
	            }
	        });
	}

}

