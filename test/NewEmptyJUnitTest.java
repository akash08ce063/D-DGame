/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.Player;
import com.game.models.Weapon;
import com.game.ui.views.InitCharacterAndMapPanel;
import com.game.ui.views.InventoryPanel;
import com.game.ui.views.MapEditor;
import com.game.ui.views.SimpleDialog;
import com.game.ui.views.WeaponEditorPanel;
import com.game.util.GameUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.xml.bind.JAXBException;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 韩信
 */
public class NewEmptyJUnitTest extends TestCase{
   public   InitCharacterAndMapPanel panel ;
   public   Weapon w;
   public   Player in ;
   public   Armour a;
   public   InventoryPanel ip;
   public   Inventory iin;
   public   MapEditor me;
   public   JCheckBox jc;
   public   WeaponEditorPanel wep;
    public NewEmptyJUnitTest() throws Exception {
          try {
            GameBean.doInit();
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
          panel = new InitCharacterAndMapPanel() ;
          in = new Player();
          iin = new Inventory();
          w = new Weapon();
          w.setAttackPts(12);
          w.setAttackRange(2);
          w.setName("Sword");
          w.setWeaponType("Meele");
          a = new Armour();
          a.setName("armor");
          iin.setEquippedWeapon(w);
          iin.setChest(a);
          in.setInventory(iin);
          jc = new JCheckBox();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
     public void testgetPositionOfArmourItem(){
       Assert.assertTrue(GameUtils.getPositionOfArmourItem("Iron Helmet") > -1);
    }
    public void testgetPositionOfRingItem(){
        
       Assert.assertTrue(GameUtils.getPositionOfRingItem("Gold Ring1") > -1);
    }
    public void testgetPositionOfTreasureItem(){
        
       Assert.assertTrue(GameUtils.getPositionOfTreasureItem("Large Loot") > -1);
    }
    public void testgetPositionOfWeaponItem(){
      
       Assert.assertTrue(GameUtils.getPositionOfWeaponItem("Long Sword") > -1);
    }
     public void testgetAllItems(){
        try {
           ArrayList<Item> Items1 = (GameUtils.getAllItems(Configuration.PATH_FOR_TREASURES));
           ArrayList<Item> Items2 = (GameUtils.getAllItems(Configuration.PATH_FOR_RINGS));
           ArrayList<Item> Items3 = (GameUtils.getAllItems(Configuration.PATH_FOR_POTIONS));
           ArrayList<Item> Items4 = (GameUtils.getAllItems(Configuration.PATH_FOR_ARMOURS));
         
           Assert.assertTrue(Items1.size() > 0);
           Assert.assertTrue(Items2.size() > 0) ;
           Assert.assertTrue(Items3.size() > 0) ;
           Assert.assertTrue(Items4.size() > 0) ;
         
        } catch (JAXBException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void testloadingMap() throws Exception{
        
     Assert.assertTrue(panel.getCharacterNames()!= null );
    }
    public void testgetMapNames() throws Exception{
     Assert.assertTrue(panel.getMapNames(Configuration.PATH_FOR_MAP) != null );
    }
    public void testmakingInformationOfWeapon(){
     ip = new InventoryPanel(in);
     ip.dispose();
     Assert.assertTrue(ip.makingInformationOfWeapon(w) != null);
    }
    public void testmakingInformationOfArmor(){
     ip = new InventoryPanel(in);
     ip.dispose();
     Assert.assertTrue(ip.makingInformationOfArmor(a) != null);
    }
    
     public void testputInventoriesIntoItem(){
     ip = new InventoryPanel(in);
     ip.dispose();
     Assert.assertTrue(ip.putInventoriesIntoItem());
    }
    
    public void testmakingInformationForOthers(){
     ip = new InventoryPanel(in);
     ip.dispose();
     Assert.assertTrue(ip.makingInformationForOthers("name") != null);
    }
    
     public void testmakingSimpleDialog() throws IOException{
     me = new MapEditor();
     me.dispose();
     Assert.assertTrue(me.getDialog() != null);
     }
     
     public void testmakingMapPanel() throws IOException{
     me = new MapEditor();
     me.dispose();
     Assert.assertTrue(me.getBottomPanel() != null);
     }
     
     public void testmakingWeaponBox() throws IOException{
     wep = new WeaponEditorPanel(1, jc);
     Assert.assertTrue(wep.getComboBox() != null);
     }
     
     public void testmakingWeaponEditorLable() throws IOException{
     wep = new WeaponEditorPanel(1, jc);
     Assert.assertTrue(wep.getValidationMess() != null);
     }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
