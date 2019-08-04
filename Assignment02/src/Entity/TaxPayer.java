
package Entity;


public class TaxPayer implements Comparable<TaxPayer>{
    String code;
    String name;
    double income;
    double deduct;
    double tax;

    public TaxPayer() {
    }

    public TaxPayer(String code, String name, double income) {
        this.code = code;
        this.name = name;
        this.income = income;
        setDeduct(income);
        setTax(income);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getIncome() {
        return income;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncome(double income) {
        this.income = income;
    }
    
    private void setTax(double income){
        if (income <= 5000){
            tax = income - ((income*5)/100 - deduct);
        }
        if (income > 5000 && income < 10000){
            tax = income - ((income*10)/100 - deduct);
        }
        if (income >= 10000){
            tax = income - ((income*15)/100 - deduct);
        }
    }
    
    private void setDeduct(double income){
        if (income <= 5000){
            deduct = 50;
        }
        if (income > 5000 && income < 10000){
            deduct = 200;
        }
        if (income >= 10000){
            deduct = 500;
        }        
    }
    
    public double getDeduct(){
        return this.deduct;
    }
    
    public double getTax(){
        return this.tax;
    }

    @Override
    public String toString() {
        System.out.printf("%-20s%-30s%-20s%-20s%-20s\n", code + "   |", name + "   |", income + "   |", deduct + "   |", tax);
        return "";
    }

    @Override
    public int compareTo(TaxPayer t) {
        return this.code.compareTo(t.getCode());
//        Integer other = t.getIncome();
//        Integer tax = this.getIncome();
//        return tax.compareTo(other);
    }
    
    
}
