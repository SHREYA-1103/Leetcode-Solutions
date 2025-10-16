public class TakingMaxEnergyFromMysticDungeon_3147 {

    public static int maxEnergy(int energy[], int k){
        int n = energy.length;
        
        int max = Integer.MIN_VALUE;
        
        for(int i=n-1; i>=0; i--){
            energy[i] = energy[i] + ((i+k) < n ? energy[i+k] : 0);
            max = Math.max(max, energy[i]);
        }

        return max;
    }
    
    public static void main(String args[]){
        int energy[] = {5,2,-10,-5,1};
        int k = 3;

        System.out.println(maxEnergy(energy, k));
    }
}
