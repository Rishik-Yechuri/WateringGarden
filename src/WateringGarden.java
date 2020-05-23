import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WateringGarden {
    public static void main(String args[]){
        double biggestValue = 0;
        //Starter data that can be cleared and reused
        int howManySections =0;
        int whereInSection = 0;
        double[][] storeSection = new double[100000][1000];
        Scanner scanner = new Scanner(System.in);
        double height = scanner.nextDouble();
        double width = scanner.nextDouble();
        double[][] originalMap = new double[(int)height][(int)width];
        for(int x=0;x<height;x++){
            for(int y=0;y<width;y++){
                originalMap[x][y] = scanner.nextDouble();
            }
        }
        double elements = width*height;

        for(int x=0;x<height;x++){
            for(int y=0;y<width;y++){
                if(originalMap[x][y]>biggestValue){
                    biggestValue = originalMap[x][y];
                }
            }
        }
        //End of starter pack data

        //loop for every possible number
        //gap to create peace
        for(int p=1;p<=biggestValue;p++){
            //Cleanse the previous data sets
            howManySections = 0;
            whereInSection = 0;
            for(int clear=0;clear<100000;clear++){
                Arrays.fill(storeSection[clear],0);
            }
            int total =0;
            double[][] xArray = new double[originalMap.length][originalMap[0].length];
            for (int r = 0; r < xArray.length; r++) {
                for (int c = 0; c < xArray[0].length; c++) {
                    xArray[r][c] = originalMap[r][c];
                }
            }


            ArrayList<String> smallGroup = new ArrayList<String>();
            //loop that calls floodFill
            for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){
                    if(xArray[y][x]!=1.5){
                        ArrayList<String> bigGroup = new ArrayList<>();
                        System.out.println(Arrays.deepToString(xArray));
                        System.out.println("Starting Water: " + p);
                        System.out.println(whereInSection);
                        ArrayList<String> pull = floodFill(xArray,y,x,bigGroup,false);
                        double[][] newRender = floodFill2(xArray,y,x,bigGroup,false);
                        double[] doubleList = new double[pull.size()];
                        for (int i = 0; i < doubleList.length; ++i) {
                            doubleList[i] = Double.parseDouble(pull.get(i));
                        }
                        storeSection[whereInSection] = doubleList;
                        xArray = newRender;
                        System.out.println("Size of Pull: " + pull.size());
                        System.out.println(Arrays.toString(doubleList));
                        whereInSection++;
                    }
                }
            }
        }
    }


    public static ArrayList<String> floodFill(double[][] xArray, int y, int x, ArrayList<String> smallGroup,boolean checked){
        String transform = String.valueOf(xArray[y][x]);
        //Statements to look in all four directions and recurse
        if(xArray[y][x]!=1.5){
            smallGroup.add(transform);
            xArray[y][x]=1.5;
            if(y-1>=0&&xArray[y-1][x]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y-1,x,smallGroup,false);
            }
            if(y+1<xArray.length&&xArray[y+1][x]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y+1,x,smallGroup,false);
            }
            if(x-1>=0&&xArray[y][x-1]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y,x-1,smallGroup,false);
            }
            if(x+1<xArray[0].length&&xArray[y][x+1]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y,x+1,smallGroup,false);
            }
            if(!checked){
                return smallGroup;
            }
        }
return smallGroup;
    }

    public static double[][] floodFill2(double[][] xArray, int y, int x, ArrayList<String> smallGroup,boolean checked){
        String transform = String.valueOf(xArray[y][x]);
        //Statements to look in all four directions and recurse
        if(xArray[y][x]!=1.5){
            smallGroup.add(transform);
            xArray[y][x]=1.5;
            if(y-1>=0&&xArray[y-1][x]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y-1,x,smallGroup,false);
            }
            if(y+1<xArray.length&&xArray[y+1][x]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y+1,x,smallGroup,false);
            }
            if(x-1>=0&&xArray[y][x-1]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y,x-1,smallGroup,false);
            }
            if(x+1<xArray[0].length&&xArray[y][x+1]!=1.5){
                checked = true;
                smallGroup = floodFill(xArray,y,x+1,smallGroup,false);
            }
            if(!checked){
                return xArray;
            }
        }
        return xArray;
    }
}