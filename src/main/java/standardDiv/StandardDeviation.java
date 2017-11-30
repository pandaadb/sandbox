package standardDiv;

import java.util.Arrays;

public class StandardDeviation {

    private  int count = 0;
    private  double mean = 0.0;
    private  double sum = 0.0;

    public  void setValues(double data) {
        ++count;
        double delta = mean + (data - mean) / count;
        sum += (data - mean) * (data - delta);
        mean = delta;
    }

    private  Double getVariancePopulation() {
        // for 'population' standard deviation calculation
        return sum / (count);
    }

    private  Double getVarianceSample() {
        // for 'sample' standard deviation calculation
        return sum / (count - 1);
    }

    public  Double getSDPopulation() {
        return count > 0 ? Math.sqrt(getVariancePopulation()) : 0.0;
    }

    public  Double getSDSample() {
        return count > 0 ? Math.sqrt(getVarianceSample()) : 0.0;
    }
    
    
    public static void main(String[] args) {
        
        StandardDeviation d = new StandardDeviation();
        
        Arrays.asList(2000,2200,2400,2800,3200).forEach( i -> {
            d.setValues(i);
        });
        
        System.out.println(d.getSDSample());
    }
}