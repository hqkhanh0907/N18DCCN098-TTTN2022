import { Component, OnInit } from '@angular/core';
import { ApexAxisChartSeries, ApexChart, ApexDataLabels, ApexTitleSubtitle } from 'ng-apexcharts';
import { PaymentService } from '../../../service/payment.service';

@Component({
  selector: 'app-turnover',
  templateUrl: './turnover.component.html',
  styleUrls: ['./turnover.component.css']
})
export class TurnoverComponent implements OnInit {
  public chartByYear: any;
  public chartByMonthly: any;
  public listYear: any;
  public selectYear: any;
  public selectType!: string;
  public color = '#2E93fA';
  public selectList = [{ id: '0', name: "Chart by year" }, { id: '1', name: "Monthly chart" }];
  // public selectList = [{ id: '0', name: "Chart by year" }, { id: '1', name: "Monthly chart" }, { id: '2', name: "Chart over time" }];
  //By year
  chartSeriesByYear: ApexAxisChartSeries = [{
    name: "distibuted",
    data: [],
    color: this.color
  }];
  chartDetailsByYear: ApexChart = {
    type: 'bar',
    toolbar: {
      show: false
    }
  };
  chartLabelsByYear = [];
  chartTitleByYear: ApexTitleSubtitle = {
    text: 'Year',
    align: 'center'
  };
  //By monthly
  chartSeriesByMonthly: ApexAxisChartSeries = [{
    name: "distibuted",
    data: [],
    color: this.color
  }];
  chartDetailsByMonthly: ApexChart = {
    type: 'bar',
    toolbar: {
      show: false
    }
  };
  chartLabelsByMonthly = [];
  chartTitleByMonthly: ApexTitleSubtitle = {
    text: 'Monthly',
    align: 'center'
  };
  chartDataLabels: ApexDataLabels = {
    enabled: true,
    style: {
      colors: ['#000000']
    }
  };
  constructor(private paymentService: PaymentService) { }

  async ngOnInit() {
    this.selectType = '0';
    await this.paymentService.getChartByYear().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.chartByYear = data;
      }
    });
    await this.paymentService.getListYear().toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.listYear = data;
      }
    });
    if (this.chartByYear) {
      this.chartSeriesByYear[0].data = this.chartByYear.data;
      this.chartLabelsByYear = this.chartByYear.labels;
    }
    if (this.listYear) {
      this.selectYear = this.listYear[this.listYear.length - 1];
    }
    await this.showChartByMonth(this.selectYear);
  }
  checkSelect(type: string): boolean {
    return this.selectType === type;
  }
  async showChartByMonth(year: any) {
    await this.paymentService.getChartByMonth(year).toPromise().then((data: any) => {
      if (data.statusCode === undefined) {
        this.chartByMonthly = data;
      }
    });
    if (this.chartByMonthly) {
      this.chartSeriesByMonthly[0].data = this.chartByMonthly.data;
      this.chartLabelsByMonthly = this.chartByMonthly.labels;
    }
  }
  async changeCharByMonth() {
    await this.showChartByMonth(this.selectYear);
  }
}
