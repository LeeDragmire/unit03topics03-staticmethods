import java.util.Scanner;

public class TimeDuration {
    private static int createdObjects = 0;
    private int hour = 0;
    private int min = 0;
    private int sec = 0;

    public TimeDuration(int hourVal, int minVal, int secVal) {
        hour = hourVal;
        min = minVal;
        sec = secVal;
    }

    private static TimeDuration parse2m(String arg) {
        TimeDuration output;
        int spaceCheck = arg.indexOf(" ");
        int h = arg.indexOf("h");
        int m = arg.indexOf("m");
        int s = arg.indexOf("s");
        if (spaceCheck != -1) {
            return null;
        }
        if (h != -1) {
            output = new TimeDuration(Integer.parseInt(arg.substring(0, h)), 0, 0);
        } else if (m != -1) {
            output = new TimeDuration(0, Integer.parseInt(arg.substring(0, m)), 0);
        } else if (s != -1) {
            output = new TimeDuration(0, 0, Integer.parseInt(arg.substring(0, s)));
        } else {
            return null;
        }
        return output;
    }

    private static TimeDuration parseSpace(String arg) {
        int[] output = new int[3];
        int comma = arg.indexOf(",");
        int lastComma = arg.lastIndexOf(",");
        int h = arg.indexOf("h");
        int m = arg.indexOf("m");
        int s = arg.indexOf("s");
        if (comma != -1) {
            if (comma == lastComma) {
                if (h != -1) {
                    output[0] = Integer.parseInt(arg.substring(0, h - 1));
                    if (m != -1) {
                        output[1] = Integer.parseInt(arg.substring(comma + 2, m - 1));
                    } else if (s != -1) {
                        output[2] = Integer.parseInt(arg.substring(comma + 2, s - 1));
                    } else {
                        return null;
                    }
                } else {
                  if (m != -1) {
                    output[1] = Integer.parseInt(arg.substring(0, m - 1));
                    output[2] = Integer.parseInt(arg.substring(comma + 2, s - 1));
                  } else {
                    return null;
                  }
                }
            } else {
                if (h != -1) {
                    output[0] = Integer.parseInt(arg.substring(0, h - 1));
                } else {
                    return null;
                }
                if (m != -1) {
                    output[1] = Integer.parseInt(arg.substring(comma + 2, m - 1));
                } else {
                    return null;
                }
                if (s != -1) {
                    output[2] = Integer.parseInt(arg.substring(lastComma + 2, s - 1));
                } else {
                    return null;
                }
            }
        } else {
            if (h != -1) {
                output[0] = Integer.parseInt(arg.substring(0, h - 1));
            } else if (m != -1) {
                output[1] = Integer.parseInt(arg.substring(0, m - 1));
            } else if (s != -1) {
                output[2] = Integer.parseInt(arg.substring(0, s - 1));
            }
        }
        return new TimeDuration(output[0], output[1], output[2]);
    }

    public static TimeDuration parseFromString(String arg) {
        int h = 0;
        int m = 0;
        int s = 0;
        int test = arg.indexOf(":");
        if (test != -1) {
            int test2 = arg.lastIndexOf(":");
            if (test2 != test) {
                h = Integer.parseInt(arg.substring(0, test));
                m = Integer.parseInt(arg.substring(test + 1, test2));
                s = Integer.parseInt(arg.substring(test2 + 1));
            } else {
                m = Integer.parseInt(arg.substring(0, test));
                s = Integer.parseInt(arg.substring(test + 1));
            }
            createdObjects++;
            return new TimeDuration(h, m, s);
        } else {
            if (parse2m(arg) != null) {
                createdObjects++;
                return parse2m(arg);
            } else if (parseSpace(arg) != null) {
                createdObjects++;
                return parseSpace(arg);
            } else {
                System.err.println("Could not parse enter like this:");
                System.err.println("2h | 1m | 1s | 1 h, 7 s | 2:00");
                return null;
            }

        }
    }

    public String toString() {
        String output = "";
        if (hour < 10) {
            output += "0" + hour + ":";
        } else {
            output += hour + ":";
        }
        if (min < 10) {
            output += "0" + min + ":";
        } else {
            output += min + ":";
        }
        if (sec < 10) {
            output += "0" + sec;
        } else {
            output += sec;
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter time duration");
        String input = scanner.nextLine();
        TimeDuration thing = parseFromString(input);
        System.out.println(thing);

        System.out.println("Enter time duration");
        String input2 = scanner.nextLine();
        TimeDuration thing2 = parseFromString(input2);
        System.out.println(thing2);
        System.out.println(createdObjects);
    }
}
