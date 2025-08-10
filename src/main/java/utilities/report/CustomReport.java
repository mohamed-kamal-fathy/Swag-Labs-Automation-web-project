package utilities.report;

import org.testng.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomReport implements ITestListener, ISuiteListener {

    static class TestInfo {
        String name;
        String status; // PASSED, FAILED, SKIPPED
        String throwable;
        String screenshot;
        long startTime;
        long endTime;
    }

    private final Map<String, TestInfo> testMap = new ConcurrentHashMap<>();

    private String key(ITestResult result) {
        return result.getMethod().getMethodName() + "_" + result.getStartMillis();
    }

    @Override
    public void onTestStart(ITestResult result) {
        TestInfo t = new TestInfo();
        t.name = result.getMethod().getMethodName();
        t.status = "RUNNING";
        t.startTime = result.getStartMillis();
        testMap.put(key(result), t);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestInfo t = testMap.get(key(result));
        if (t != null) {
            t.status = "PASSED";
            t.endTime = result.getEndMillis();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestInfo t = testMap.get(key(result));
        if (t != null) {
            t.status = "FAILED";
            t.endTime = result.getEndMillis();
            Throwable thr = result.getThrowable();
            t.throwable = thr == null ? "" : thr.toString();


        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestInfo t = testMap.get(key(result));
        if (t != null) {
            t.status = "SKIPPED";
            t.endTime = result.getEndMillis();
        }
    }

    // not used
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) { /* do nothing here */ }

    @Override
    public void onStart(ISuite suite) { /* nothing */ }

    @Override
    public void onFinish(ISuite suite) {
        generateReport();
    }

    private void generateReport() {
        try {
            new java.io.File("reports").mkdirs();
            try (FileWriter fw = new FileWriter("reports/CustomReport.html");
                 PrintWriter out = new PrintWriter(fw)) {

                int passed = 0, failed = 0, skipped = 0;
                List<TestInfo> infos = new ArrayList<>(testMap.values());
                for (TestInfo ti : infos) {
                    if ("PASSED".equals(ti.status)) passed++;
                    else if ("FAILED".equals(ti.status)) failed++;
                    else if ("SKIPPED".equals(ti.status)) skipped++;
                }

                out.println("<!doctype html>");
                out.println("<html><head>");
                out.println("<meta charset='utf-8'>");
                out.println("<title>Automation Report</title>");
                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>");
                out.println("<style>body{padding:20px;} .card{margin-bottom:12px;} .screenshot{max-width:400px;border:1px solid #ddd;padding:4px;}</style>");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h1>Automation Test Report</h1>");
                out.printf("<p>Generated: %s</p>%n", new Date());
                out.println("<div class='row'>");
                out.println("<div class='col-md-4'>");
                out.println("<div class='card'><div class='card-body'>");
                out.printf("<h5 class='card-title'>Summary</h5>");
                out.printf("<p>Total: <strong>%d</strong></p>", (passed + failed + skipped));
                out.printf("<p class='text-success'>Passed: <strong>%d</strong></p>", passed);
                out.printf("<p class='text-danger'>Failed: <strong>%d</strong></p>", failed);
                out.printf("<p class='text-warning'>Skipped: <strong>%d</strong></p>", skipped);
                out.println("</div></div>");
                out.println("</div>");

                out.println("<div class='col-md-8'>");
                out.println("<canvas id='pieChart'></canvas>");
                out.println("</div>");
                out.println("</div>"); // row

                // tests details
                out.println("<h3>Test Details</h3>");
                for (TestInfo ti : infos) {
                    String badge = "secondary";
                    if ("PASSED".equals(ti.status)) badge = "success";
                    else if ("FAILED".equals(ti.status)) badge = "danger";
                    else if ("SKIPPED".equals(ti.status)) badge = "warning";

                    out.printf("<div class='card'><div class='card-body'>%n");
                    out.printf("<h5>%s <span class='badge bg-%s'>%s</span></h5>%n", ti.name, badge, ti.status);
                    if (ti.throwable != null) {
                        out.printf("<pre>%s</pre>%n", escapeHtml(ti.throwable));
                    }
                    if (ti.screenshot != null) {
                        out.printf("<p>Screenshot:</p><img class='screenshot' src='%s' alt='screenshot'/>%n", ti.screenshot);
                    }
                    long duration = (ti.endTime > 0 ? ti.endTime - ti.startTime : 0);
                    out.printf("<p>Duration: %d ms</p>", duration);
                    out.println("</div></div>");
                }

                // Chart.js script
                out.println("<script>");
                out.println("const ctx = document.getElementById('pieChart');");
                out.println("new Chart(ctx, { type: 'pie', data: { labels: ['Passed','Failed','Skipped'], datasets: [{ data: [" + passed + "," + failed + "," + skipped + "], backgroundColor: ['#28a745','#dc3545','#ffc107'] }] }, options: { responsive: true } });");
                out.println("</script>");

                out.println("</div></body></html>");
            }
            System.out.println("Custom report written to reports/CustomReport.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
