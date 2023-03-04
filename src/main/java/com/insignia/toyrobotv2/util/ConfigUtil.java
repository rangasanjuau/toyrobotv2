package com.insignia.toyrobotv2.util;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;


@Component
public class ConfigUtil {

    private static int tableLength;
    private static int tableBreadth;
    private static LinkedHashMap<String, List<Integer>> directionMap;
    private static LinkedHashMap<String, Integer> commands;
    private static LinkedHashMap<String, Integer> rotation;


    public static int getTableLength() {
        return tableLength;
    }

    public static void setTableLength(int tableLength) {
        ConfigUtil.tableLength = tableLength;
    }

    public static int getTableBreadth() {
        return tableBreadth;
    }

    public static void setTableBreadth(int tableBreadth) {
        ConfigUtil.tableBreadth = tableBreadth;
    }

    public static LinkedHashMap<String, List<Integer>> getDirectionMap() {
        return directionMap;
    }

    public static void setDirectionMap(LinkedHashMap<String, List<Integer>> directionMap) {
        ConfigUtil.directionMap = directionMap;
    }

    public static LinkedHashMap<String, Integer> getCommands() {
        return commands;
    }

    public static void setCommands(LinkedHashMap<String, Integer> commands) {
        ConfigUtil.commands = commands;
    }

    public static LinkedHashMap<String, Integer> getRotation() {
        return rotation;
    }

    public static void setRotation(LinkedHashMap<String, Integer> rotation) {
        ConfigUtil.rotation = rotation;
    }
}
