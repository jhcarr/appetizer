var barGap = 20;
var xBuff;

/* 
   Other charts may be added here in the same way:
   <function defining chart space>
   <rendering function>
*/

function checkinsDemonstration (location, dimension, group1, group2) {

    console.log("Begin checkinsDemonstration");

//    var dimensionGroup = dimension.group().reduceCount().orderNatural();

    return dc.barChart(location)
        .height(450)
        .width(900)
        .gap(barGap)
        .centerBar(true)
        .dimension(dimension)
        .group(group1)
        .stack(group2)
        .x(d3.time.scale().domain([new Date(2012, 9, 9), new Date(2013, 6, 20)]))
        .margins({top: 50, bottom: 50, right: 50, left: 50})
        .xAxis().tickFormat(d3.time.format("%Y-%m-%d"));
}

function drawCheckins() {

console.log(queryData);

var filtered = crossfilter(queryData);

var day = filtered.dimension( function (f) { return new Date(f.date);} );

var puppetdb_by_day = day.group(d3.time.day).reduceSum( function(p) { if ("puppetdb" == p.product) {return p.count;} else return 0; });

var pemaster_by_day = day.group(d3.time.day).reduceSum( function(p) { if ("pe-master" == p.product) {return p.count;} else return 0; });

checkinsDemonstration( '#checkins_by_date', day, puppetdb_by_day, pemaster_by_day );

dc.renderAll();

}
