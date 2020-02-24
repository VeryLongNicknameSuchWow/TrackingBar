package pl.rynbou.trackingbar.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class AngleUtil {

    public static double calculateAngle(Location seeker, Location target) {
        Vector playerVector = target
                .toVector()
                .subtract(seeker.toVector()).setY(0).normalize();

        Vector seekerDirection = seeker.getDirection().setY(0).normalize();

        double x1 = playerVector.getX();
        double x2 = seekerDirection.getX();
        double z1 = playerVector.getZ();
        double z2 = seekerDirection.getZ();

        return Math.atan2(x1 * z2 - z1 * x2, x1 * x2 + z1 * z2);
    }

    public static String getBarMessage(double angle){
//        int space = (int) map(angle, -Math.PI, Math.PI, -90, 90);
//
        if (angle < -Math.PI / 2) {
            return "<                                             ";
        } else if (angle > Math.PI / 2) {
            return "                                             >";
        } else {
            int space = (int) map(angle, -Math.PI, Math.PI, -45, 45);

            StringBuilder s = new StringBuilder();

            if (space < 0) {
                space = -space;
                for (; space > 0; space--) {
                    s.append(" ");
                }
                s.append("/\\");
            } else {
                s.append("/\\");
                for (; space > 0; space--) {
                    s.append(" ");
                }
            }

            return s.toString();
        }
    }

    public static double map(double value, double istart, double istop, double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

}
