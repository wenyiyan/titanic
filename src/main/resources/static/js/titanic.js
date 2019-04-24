// titanic passengers
// Pie Chart , Bar Chart, and jqxgrid

    $(document).ready(function () {
        // jqxgrid
        var theme = 'classic';
        var source =
            {
                type: 'GET',
                datatype: "json",
                contentType: 'application/json; charset=utf-8',
                datafields: [
                    {
                        // name:'passengerId',
                        name:'name',
                        name:'survived',
                        name:'age',
                        name:'cabin',
                        name:'embarked',
                        name:'fare',
                        name:'parch',
                        name:'pclass',
                        name:'sex',
                        name:'sibSp',
                        name:'ticket'
                    }
                ],
                cache: false,
                sortcolumn: 'name',
                sortdirection: 'asc',

                url: '/titanic/passengers/list',

                filter: function() {
                    // update the grid and send a request to the server.
                    $("#jqxgrid").jqxGrid('updatebounddata', 'filter');
                },
                sort: function() {
                    // update the grid and send a request to the server.
                    $("#jqxgrid").jqxGrid('updatebounddata', 'sort');
                },
                beforeprocessing: function (content) {
                    //根据实际做相应的调整不一定是data[0].TotalRows;建议写个debugger;调试
                    data = content["data"]["content"];
                    // console.log(data);
                    console.log(content["data"]);
                    console.log(content["data"].totalElements);
                    source.totalrecords = content["data"].totalElements;
                }
            };
        var dataadapter = new $.jqx.dataAdapter(source);

        // initialize jqxGrid
        $("#jqxgrid").jqxGrid(
            {
                width: '100%',
                sortable: true,
                source: dataadapter,
                theme: theme,
                height:350,
                // autoheight: true,
                pageable: true,
                virtualmode: true,
                rendergridrows: function (params) {
                    // console.log(dataadapter.loadedData["data"]["content"])
                    return dataadapter.loadedData["data"]["content"]
                },

                columns:
                    [
                        // { text: 'passengerId', datafield: 'passengerId', width: 250 },
                        { text: 'Name', datafield: 'name', width: 250 },
                        { text: 'Survival', datafield: 'survived', width: 200 },
                        { text: 'Age', datafield: 'age', width: 200 },
                        { text: 'Sex', datafield: 'sex', width: 200 },
                        { text: 'Number Siblings/Spouses', datafield: 'sibSp', width: 200  },
                        { text: 'Ticket Number', datafield: 'ticket', width: 200  },
                        { text: 'Fare', datafield: 'fare', width: 200  },
                        { text: 'Ticket class', datafield: 'pclass', width: 200  },
                        { text: 'Cabin Number', datafield: 'cabin', width: 200  },
                        { text: 'Embarked', datafield: 'embarked', width: 200  },
                        { text: 'Number of Parents/Children', datafield: 'parch', width: 200  }
                    ]
            });

        // bar chart
        var bar_chart_source = $.ajax({
            url: "/titanic/chart/bar/survived"
        }).then(function(res) {
            console.log(res.data);
            createBarChart(res.data);
        });

        function createBarChart(res) {
            Highcharts.chart('barChart', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Titanic Passengers'
                },
                xAxis: {
                    categories: res["categories"],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'The number of people'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} people</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        pointPadding: 0.2,
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true
                        },
                    },
                    showInLegend: true

                },
                series: res["series"]
            });
        };

        // pie chart
        var pie_chart_source = $.ajax({
            url: "/titanic/chart/pie/survived"
        }).then(function(res) {
            console.log(res);
            createPieChart(res);
        });

        function createPieChart(res) {
            Highcharts.chart('pieChart', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: 'The Survival Percentage of Titanic Passengers'
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.percentage} %</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true,
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        pointPadding: 0.2,
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '<br>{point.percentage}%</b>'
                        },
                        showInLegend: true
                    }
                },
                series: [{
                    name: 'Passengers',
                    colorByPoint: true,
                    data: res.data
                }]
            });
        };

    });

