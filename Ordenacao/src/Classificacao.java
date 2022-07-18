
public class Classificacao {
    
    
    public static int bubblesort1(int v[]) {
        int count = 0;

        for (int i = 0; i < v.length - 1; i++) {
            for (int j = i + 1; j < v.length; j++) {
                count++;
                if (v[i] > v[j]) {
                    int aux = v[i];
                    v[i] = v[j];
                    v[j] = aux;
                }
            }
        }
        return count;
    }

    public static int[] bubblesort2(int v[]) {
        boolean troca;
        do {
            troca = false;
            for (int i = 0; i < v.length - 1; i++) {
                
                if (v[i] > v[i + 1]) {
                    int aux = v[i];
                    v[i] = v[i + 1];
                    v[i + 1] = aux;
                    troca = true;
                }
            }
        } while (troca);
        return v;
    }

    public static int insertionsort(int v[]) {
        int count = 0;
        int pivo, j;

        for (int i = 1; i < v.length; i++) {
            count++;
            pivo = v[i];
            j = i - 1;
            while (j >= 0 && pivo < v[j]) {
                count++;
                v[j + 1] = v[j];
                j--;
            }
            v[j + 1] = pivo;
        }
        //System.out.println("Tamanho: " + v.length + "; insertionsort; Operações: " + count);
        return count;
    }

    public static int mergesort(int v[]) {
        Contador count = new Contador(0);
        mergesort(v, 0, v.length - 1, count);
        return count.count;
    }

    public static void mergesort(int v[], int inicio, int fim, Contador count) {
        int meio = (inicio + fim) / 2;

        if (inicio < fim) {
            mergesort(v, inicio, meio, count);
            mergesort(v, meio + 1, fim, count);

            merge(v, inicio, meio, fim, count);
        }
    }

    public static void merge(int v[], int inicio, int meio, int fim, Contador count) {
        int aux[] = new int[(fim + 1) - inicio];
        int i = inicio, j = meio + 1, k = 0;

        while (i <= meio && j <= fim) {
            count.count++;
            aux[k++] = v[i] <= v[j] ? v[i++] : v[j++];
        }

        while (i <= meio) {
            count.count++;
            aux[k++] = v[i++];
        }

        while (j <= fim) {
            count.count++;
            aux[k++] = v[j++];
        }

        for (i = inicio, k = 0; i <= fim; i++, k++) {
            v[i] = aux[k];
        }
    }

    public static void mergesortBU(int v[]) {
        Contador count = new Contador(0);
        mergesortBU(v, 0, v.length - 1,count);
    }

    public static void mergesortBU(int v[], int inicio, int fim, Contador count) {
        int i, meio;

        for (meio = 1; meio < fim - inicio + 1; meio = 2 * meio) {
            for (i = inicio; i <= fim - meio; i += 2 * meio) {
                int j = (i + (2 * meio)) - 1;
                if (j > fim)
                    j = fim;

                merge(v, i, i + meio - 1, j, count);
            }
        }
    }

    public static int quicksort(int v[]) {
        Contador count = new Contador(0);
        quicksort(v, 0, v.length - 1, count);
        return count.count;
    }

    public static void quicksort(int v[], int inicio, int fim, Contador count) {
        //count.count++;
        if (inicio < fim) {
            int meio = partition(v, inicio, fim, count);

            quicksort(v, inicio, meio - 1, count);
            quicksort(v, meio + 1, fim, count);
        }
    }

    public static int partition(int v[], int inicio, int fim, Contador count) {
        //count.count++;
        int pivo = v[fim];
        int i = (inicio - 1);
        
        for (int j = inicio; j <= fim - 1; j++) {
            if (v[j] < pivo) {
                //count.count++;
                i++;
                swap(v, i, j, count);
            }
        }
        swap(v, i + 1, fim, count);
        return i + 1;
    }
    
    public static void swap(int v[], int i, int j, Contador count) {
        count.count++;
        int aux = v[j];
        v[j] = v[i];
        v[i] = aux;
    }

    public static void heapify(int v[], int n, int i, Contador count) {
        count.count++;
        int raiz = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
        

        if (esquerda < n && v[esquerda] > v[raiz])
            raiz = esquerda;

        if (direita < n && v[direita] > v[raiz])
            raiz = direita;

        if (raiz != i) {
            swap(v, i, raiz, count);
            heapify(v, n, raiz, count);
        }
    }

    public static int heapsort(int v[]) {
        Contador count = new Contador(0);

        for (int i = v.length / 2 - 1; i >= 0; i--) {
            heapify(v, v.length, i, count);
        }
        for (int i = v.length - 1; i > 0; i--) {
            swap(v, 0, i, count);
            heapify(v, i, 0, count);
        }
        //System.out.println("Tamanho: " + v.length + "; heapsort; Operações: " + count.getCount());

        return count.count;
    }

    public static int max(int v[]) {
        int max = v[0];

        for (int i = 1; i < v.length; i++) {
            if (v[i] > max)
                max = v[i];
        }

        return max;
    }

    public static void countsort(int v[], int exp) {
        int output[] = new int[v.length], count[] = new int[10];

        for (int i = 0; i < v.length; i++) {
            count[(v[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = v.length - 1; i >= 0; i--) {
            output[count[(v[i] / exp) % 10] - 1] = v[i];
            count[(v[i] / exp) % 10]--;
        }

        for (int i = 0; i < v.length; i++) {
            v[i] = output[i];
        }
    }

    public static void radixsort(int v[]) {
        int max = max(v);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countsort(v, exp);
        }
    }

    public static void coutingsort(int v[]) {
        int output[] = new int[v.length];

        int count[] = new int[max(v) + 1];

        for (int i = 0; i < v.length; i++)
            count[v[i]]++;

        for (int i = 1; i < count.length; ++i)
            count[i] += count[i - 1];

        for (int i = v.length - 1; i >= 0; i--) {
            output[count[v[i]] - 1] = v[i];
            --count[v[i]];
        }

        for (int i = 0; i < v.length; ++i)
            v[i] = output[i];
    }

    public static int[] worstcase(int n) {
        int v[] = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = n - i;
        }

        return v;
    }

    public static int[] averagecase(int n) {
        int v[] = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = (int) (Math.random() * n);
        }

        return v;
    }

    public static void print(int v[]) {
        System.out.print("[ ");

        for (int i : v) {
        System.out.print(i + " ");
        }

        System.out.println("]");
    }
    
    public static void gerarCasoMedioBubleSort(){
        long [] listaMediaBubleSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgB[] = averagecase(j);
                listaMediaBubleSort[j] += bubblesort1(avgB);
            }
        }
        for (int i = 1; i < listaMediaBubleSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaBubleSort[i]/10));
        }
    }

    public static void gerarCasoMedioInsertionsort(){
        long [] listaMediaInsertionSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgI[] = averagecase(j);
                listaMediaInsertionSort[j] += insertionsort(avgI);
            }
        }
        for (int i = 1; i < listaMediaInsertionSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaInsertionSort[i]/10));
        }
    }

    public static void gerarCasoMedioHeapSort(){
        long [] listaMediaHeapSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgH[] = averagecase(j);
                listaMediaHeapSort[j] += heapsort(avgH);
            }
        }
        for (int i = 1; i < listaMediaHeapSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaHeapSort[i]/10));
        }
    }
    public static void gerarCasoMedioMergeSort(){
        long [] listaMediaMergeSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgM[] = averagecase(j);
                listaMediaMergeSort[j] += mergesort(avgM);
            }
        }
        for (int i = 1; i < listaMediaMergeSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaMergeSort[i]/10));
        }
    }

    public static void gerarCasoMedioQuikSort(){
        long [] listaMediaQuikSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgQ[] = averagecase(j);
                listaMediaQuikSort[j] += quicksort(avgQ);
            }
        }
        for (int i = 1; i < listaMediaQuikSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaQuikSort[i]/10));
        }
    }

    public static void gerarCasoDificioBubleSort(){
        long [] listaMediaBubleSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int wtcB[] = worstcase(j);
                listaMediaBubleSort[j] += bubblesort1(wtcB);
            }
        }
        for (int i = 1; i < listaMediaBubleSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaBubleSort[i]/10));
        }
    }

    public static void gerarCasoDificioInsertionsort(){
        long [] listaMediaInsertionSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int wtcI[] = worstcase(j);
                listaMediaInsertionSort[j] += insertionsort(wtcI);
            }
        }
        for (int i = 1; i < listaMediaInsertionSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaInsertionSort[i]/10));
        }
    }

    public static void gerarCasoDificioHeapSort(){
        long [] listaMediaHeapSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int wtcH[] = worstcase(j);
                listaMediaHeapSort[j] += heapsort(wtcH);
            }
        }
        for (int i = 1; i < listaMediaHeapSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaHeapSort[i]/10));
        }
    }

    public static void gerarCasoDificioMergeSort(){
        long [] listaMediaMergeSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgM[] = worstcase(j);
                listaMediaMergeSort[j] += mergesort(avgM);
            }
        }
        for (int i = 1; i < listaMediaMergeSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaMergeSort[i]/10));
        }
    }
    public static void gerarCasoDificioQuikSort(){
        long [] listaMediaQuikSort = new long[1001];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 1000; j++) {
                int avgQ[] = worstcase(j);
                listaMediaQuikSort[j] += quicksort(avgQ);
            }
        }
        for (int i = 1; i < listaMediaQuikSort.length; i++) {
            System.out.println(i+" ; "+ (listaMediaQuikSort[i]/10));
        }
    }

    public static void main(String[] args) {

        //gerarCasoMedioBubleSort();
        //gerarCasoDificioBubleSort();
        //gerarCasoMedioInsertionsort();
        //gerarCasoDificioInsertionsort();
        //gerarCasoMedioHeapSort();
        //gerarCasoDificioHeapSort();
        //gerarCasoMedioMergeSort();
        //gerarCasoDificioMergeSort();
        //gerarCasoMedioQuikSort();
        gerarCasoDificioQuikSort();

    }


}
