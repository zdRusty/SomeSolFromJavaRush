package Level_24.task06;

import java.math.BigDecimal;

/*
Наследие внутреннего класса
*/
public class Solution {
    public class Building {
        public class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }

        public class Apartments {
        }
    }

    public static void main(String[] args) {

    }

    public class Apt3Bedroom extends Building.Apartments{
        public Apt3Bedroom(Building building){
            building.super();
        }
    }

    public class BigHall extends Building.Hall{
        public BigHall(Building building){
            building.super(new BigDecimal(50));
        }
    }
}