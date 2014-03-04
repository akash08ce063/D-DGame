 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.util;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.GameCharacter;
import com.game.models.Item;
import com.game.models.MapInformation;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Treasure;
import com.game.models.Weapon;
import com.game.xml.models.CharacterWrapper;
import com.game.xml.models.ItemWrapper;
import com.game.xml.models.MapInformationWrapper;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Kaushik
 */
public class GameUtils {

    public static MapInformation getMapInformation(String mapName) {
        return new MapInformation();
    }

    public static ArrayList<GameCharacter> getCharacterDetailsFromFile(String fileName) throws JAXBException, Exception {
        ArrayList<GameCharacter> charList = null;
        try {
            JAXBContext context = JAXBContext.newInstance(CharacterWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            CharacterWrapper wrapper = (CharacterWrapper) um.unmarshal(new File(fileName));
            charList = wrapper.getCharacter();
        } catch (JAXBException e) {
            System.out.println("GameUtils : getCharacterDetailsFromFile() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : getCharacterDetailsFromFile() : Execption occured : " + e);
            throw new Exception(e);
        }
        return charList;
    }

    public static ArrayList<Item> getAllItems(String fileName) throws JAXBException, Exception {
        ArrayList<Item> itemList = null;
        try {
            JAXBContext context = JAXBContext.newInstance(ItemWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            ItemWrapper wrapper = (ItemWrapper) um.unmarshal(new File(fileName));
            if(wrapper == null)
            itemList = wrapper.getItem();
        } catch (JAXBException e) {
            System.out.println("GameUtils : getAllItems() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : getAllItems() : Execption occured : " + e);
            throw new Exception(e);
        }
        return itemList;
    }

    public static void writeCharactersToXML(ArrayList<GameCharacter> characters, String fileName) throws JAXBException, Exception {
        CharacterWrapper wrapper = new CharacterWrapper();
        wrapper.setCharacter(characters);
        try {
            JAXBContext context = JAXBContext.newInstance(CharacterWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(wrapper, new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeCharactersToXML() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeCharactersToXML() : Execption occured : " + e);
            throw new Exception(e);
        }
    }

    public static void writeItemsToXML(ArrayList<Item> items, String fileName) throws JAXBException, Exception {
        ItemWrapper wrapper = new ItemWrapper();
        wrapper.setItem(items);
        try {
            JAXBContext context = JAXBContext.newInstance(ItemWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(wrapper, new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeItemsToXML() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeItemsToXML() : Execption occured : " + e);
            throw new Exception(e);
        }
    }

    public static void updateCharacterDetails(ArrayList<GameCharacter> characters, String fileName) throws JAXBException, Exception {
        ArrayList<GameCharacter> charList = null;
        try {
            charList = getCharacterDetailsFromFile(fileName);
            charList.addAll(characters);
            writeCharactersToXML(charList, fileName);
        } catch (JAXBException e) {
            System.out.println("GameUtils : updateCharacterDetails() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : updateCharacterDetails() : Execption occured : " + e);
            throw new Exception(e);
        }
    }

    public static void updateItemsXml(ArrayList<Item> items, String fileName) throws JAXBException, Exception {
        ArrayList<Item> itemList = null;
        try {
            itemList = getAllItems(fileName);
            itemList.addAll(items);
            writeItemsToXML(itemList, fileName);
        } catch (JAXBException e) {
            System.out.println("GameUtils : updateItemsXml() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : updateItemsXml() : Execption occured : " + e);
            throw new Exception(e);
        }
    }

    public static ImageIcon shrinkImage(String fileName, int width, int height) throws IOException {
        StringBuilder imagePath = new StringBuilder(Configuration.PATH_FOR_IMAGES);
        imagePath.append(fileName);
        System.out.println(imagePath.toString());
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagePath.toString()));
        } catch (IOException e) {
            System.out.println("GameUtils : shrinkImage() : Exception Occured : " + e);
            throw new IOException(e);
        }
        return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    public static int getPositionOfWeaponItem(String name) {
        int position = -1;
        if (GameBean.weaponDetails != null) {
            for (int i = 0; i < GameBean.weaponDetails.size(); i++) {
                Weapon weapon = (Weapon) GameBean.weaponDetails.get(i);
                if (weapon.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
    public static int getPositionOfRingItem(String name) {
        int position = -1;
        if (GameBean.ringDetails != null) {
            for (int i = 0; i < GameBean.ringDetails.size(); i++) {
                Ring ring = (Ring) GameBean.ringDetails.get(i);
                if (ring.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    public static int getPositionOfArmourItem(String name) {
        int position = -1;
        if (GameBean.armourDetails != null) {
            for (int i = 0; i < GameBean.armourDetails.size(); i++) {
                Armour armour = (Armour) GameBean.armourDetails.get(i);
                if (armour.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    public static int getPositionOfTreasureItem(String name) {
        int position = -1;
        if (GameBean.treasureDetails != null) {
            for (int i = 0; i < GameBean.treasureDetails.size(); i++) {
                Treasure treasure = (Treasure) GameBean.treasureDetails.get(i);
                if (treasure.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    public static int getPositionOfPotionItem(String name) {
        int position = -1;
        if (GameBean.potionDetails != null) {
            for (int i = 0; i < GameBean.potionDetails.size(); i++) {
                Potion potion = (Potion) GameBean.potionDetails.get(i);
                if (potion.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    public static void writeMapInformation(MapInformationWrapper wrapper, String fileName) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(MapInformationWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(wrapper, new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new Exception(e);
        }
    }

    public static MapInformationWrapper readMapInformation(String fileName) throws Exception {
        MapInformationWrapper mapInfo = null;
        try {
            JAXBContext context = JAXBContext.newInstance(MapInformationWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            mapInfo = (MapInformationWrapper) um.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new Exception(e);
        }
        return mapInfo;
    }

    public static MapInformation fetchParticularMapData(String fileName, String mapName) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(MapInformationWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            MapInformationWrapper wrapper = (MapInformationWrapper) um.unmarshal(new File(fileName));
            ArrayList<MapInformation> list = wrapper.getMapList();
            for (MapInformation info : list) {
                if (info.getMapName().equalsIgnoreCase(mapName)) {
                    return info;
                }
            }

        } catch (JAXBException e) {
            System.out.println("GameUtils : fetchParticularMapData() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : fetchParticularMapData() : Execption occured : " + e);
            throw new Exception(e);
        }
        return null;
    }
}
