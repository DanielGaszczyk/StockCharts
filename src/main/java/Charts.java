import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONException;
import java.io.IOException;

public class Charts extends ApplicationFrame {

    public Charts( String applicationTitle , String chartTitle ) throws IOException, JSONException {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Time","Price",
                createDataset(),
                PlotOrientation.VERTICAL,
                false,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 1280 , 720 ) );        //TODO Center align graph
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset( ) throws IOException, JSONException {
        getJSON getJson= new getJSON();
        double[] priceArray = getJson.getPriceArray();
        String[] dateArray = getJson.getDateArray();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for (int i = 0; i < getJson.daysRange; i++) {
            dataset.addValue(priceArray[i], "Price", dateArray[i]);
        }
        return dataset;
    }

    public static void main( String[ ] args ) throws IOException, JSONException {
        String stockName = getJSON.stockName;               //Give stock name and move away
        Charts chart = new Charts(
                "Stock chart" ,
                stockName);

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}