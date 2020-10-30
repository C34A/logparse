import java.util.*;
import java.io.*;

public class LogParse {
    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(args));
            Scanner s;
            if(args.length == 0) {
                s = new Scanner(new File("Player.log"));
            } else {
                s = new Scanner(new File(args[0]));
            }

            parse(s);
        } catch(Exception e) {
            System.out.println("failed to get file (probably)");
            System.out.println(e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            System.out.println(sStackTrace);
        }
    }

    public static void parse(Scanner s) {
        String currentTileset = "";
        String currentMap = "";
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String line_substr = "";
//            try {
                if(line.substring(6, 18).equals("Done Loading")) {
                    String newMap = line.substring(line.indexOf(']') + 2, line.lastIndexOf('[') - 1);
                    if(!newMap.equals(currentMap)) {
                        System.out.println("round change!");
                        currentMap = newMap;
                        currentTileset = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
                        System.out.println("new tileset: " + currentTileset + "\nnew map: " + currentMap);
                    }
                }

                if(line.substring(0, 6).equals("Killer")) {
                    String killerSide = line.substring(line.indexOf(':') + 2, line.indexOf(','));
                    String victimSide = line.substring(line.lastIndexOf(':') + 2);
                    String killInfoLine = s.nextLine();
                    System.out.println(killerSide + " killed " + victimSide + ": \n" + killInfoLine);
                }
//            } catch (Exception e) {
//
//            }
        }
    }

    public static Team getTeam(String raw) {
        return switch(raw) {
            case "ATTACKER" -> Team.Attacker;
            case "DEFENDER" -> Team.Defender;
            case "NONE" -> Team.None;
            default -> Team.Unknown;
        };
    }

    public static enum Team {
        Attacker,
        Defender,
        None,
        Unknown,
    }
}
