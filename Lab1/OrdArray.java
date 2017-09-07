package Lab1;

class OrdArray {

    private long[] a;               //ref to array a
    private int nElems;             //number of data items
    //---------------------------------------------------------------
    public OrdArray(int max) {

        a = new long[max];          //create array
        nElems = 0;
    }
    //---------------------------------------------------------------
    public int size() {

        return nElems;

    }
    //---------------------------------------------------------------
    public int find(long searchKey) {

        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while(true) {

            curIn = (lowerBound + upperBound) / 2;

            if(a[curIn] == searchKey)
                return curIn;      //found it
            else if(lowerBound > upperBound)
                return nElems;      //cant find it
            else {

                if (a[curIn] < searchKey)
                    lowerBound = curIn + 1;     //its in upper half
                else
                    upperBound = curIn - 1;    //its in lower half

            } //end else

        } //end while
    } //end find()
    //---------------------------------------------------------------
    public void insert(long value) {        //put element into array

        int lowerBound = 0;
        int upperBound = nElems - 1;
        int j = 0;
        while(true)
        {
            if(lowerBound > upperBound)
                break;

            j = (lowerBound + upperBound) / 2;

            if(value > a[j])
            {
                lowerBound = j + 1;
                j++;
            }
            else upperBound = j - 1;
        }
        for(int k = nElems; k > j; k--)       //move bigger ones up
            a[k] = a[k - 1];
        a[j] = value;                       //insert it
        nElems++;                           //increment size
    } //end insert()
    //---------------------------------------------------------------
    public boolean delete(long value) {

        int j = find(value);

        if(j == nElems)         //cant find it
            return false;
        else {

            for(int k = j; k < nElems; k++)     //move bigger ones down
                a[k] = a[k + 1];
            nElems--;
            return true;
        }

    } //end delete()
    //---------------------------------------------------------------
    public void display() {                 //displays array contents

        for(int j = 0; j < nElems; j++)
            System.out.print(a[j] + " ");   //display it
        System.out.println("");
    }
} // end class OrdArray
///////////////////////////////////////////////////////////////////////
class OrderedApp {
    public static void main(String[] args) {

        int maxSize = 100;              //array size
        OrdArray arr;                   //reference to array

        arr = new OrdArray(maxSize);    //create the array

        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        int searchKey = 55;             //search for item

        if(arr.find(searchKey) != arr.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.display();                  //display items

        arr.delete(00);             //delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                  //display items again
    } //end main()
} //end class OrderedApp
//////////////////////////////////////////////////////////////////////