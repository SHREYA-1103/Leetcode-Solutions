public class MinTimeToBrewPotions_3494{

    public static long minTime(int skills[], int mana[]){
        int n = skills.length;

        long[] time = new long[n];

        for (int x : mana) {
            time[0] = time[0] + 1L * skills[0] * x;

            for (int i = 1; i < n; i++) {
                time[i] = Math.max(time[i], time[i - 1]) + 1L * skills[i] * x;
            }

            for (int i = n - 2; i >= 0; i--) {
                time[i] = time[i + 1] - 1L * skills[i + 1] * x;
            }
        }
        
        return time[n - 1];
    }

    public static void main(String args[]){
        int skills[] = {1,5,2,4};
        int mana[] = {5,1,4,2};

        System.out.println(minTime(skills, mana));
    }
}