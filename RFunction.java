 


/**
 * Generates a number from an array on a cyclic basis
 *
 * @author Maximilian Kronborg
 * @version 1.0
 */
public class RFunction
{
    int count; // Counter variable for which number to return
    int [] random = {1804289383, 846930886, 1681692777, 1714636915, 1957747793, 424238335, 719885386, 1649760492,
            596516649, 1189641421, 1025202362, 1350490027, 783368690, 1102520059, 2044897763, 1967513926,
            1365180540, 1540383426, 304089172, 1303455736, 35005211, 521595368, 294702567, 1726956429, 
            336465782, 861021530, 278722862, 233665123, 2145174067, 468703135, 1101513929, 1801979802, 
            1315634022, 635723058, 1369133069, 1125898167, 1059961393, 2089018456, 628175011, 1656478042, 
            1131176229, 1653377373, 859484421, 1914544919, 608413784, 756898537, 1734575198, 1973594324, 
            149798315, 2038664370, 1129566413, 184803526, 412776091, 1424268980, 1911759956, 749241873, 
            137806862, 42999170, 982906996, 135497281, 511702305, 2084420925, 1937477084, 1827336327, 
            572660336, 1159126505, 805750846, 1632621729, 1100661313, 1433925857, 1141616124, 84353895, 
            939819582, 2001100545, 1998898814, 1548233367, 610515434, 1585990364, 1374344043, 760313750, 
            1477171087, 356426808, 945117276, 1889947178, 1780695788, 709393584, 491705403, 1918502651, 
            752392754, 1474612399, 2053999932, 1264095060, 1411549676, 1843993368, 943947739, 1984210012, 
            855636226, 1749698586, 1469348094}; // Arrray producing numbers for the 'r' input, which
                                                // were not at all fun to collect from the supplied program....
    /**
     * Constructor for objects of class RFunction
     */
    public RFunction()
    {
        count = 0;
    }

    /**
     * Generates a number from array random, on a cyclic basis
     *
     */
    public int randomNumber()
    {
        if (count > 98) // Once the last number has been printed, r is reset
                count = 0;
        int thisRandom = random[count];
        count++; // As the number printed is cyclic the next value will be the next number
        return thisRandom;
    }
    /**
     * @return thisRandom
     *          The next number form the array to be returned
     */
}
