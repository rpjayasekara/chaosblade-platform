import { Component, OnInit } from '@angular/core';
import {ExperimentService} from '../experiment.service';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-hostmanager',
  templateUrl: './hostmanager.component.html',
  styleUrls: ['./hostmanager.component.css']
})
export class HostmanagerComponent implements OnInit {

  public hosts;
  // tslint:disable-next-line:variable-name
  private _success = new Subject<string>();

  public type;
  staticAlertClosed = false;
  successMessage = '';
  constructor(private experimentService: ExperimentService) { }

  ngOnInit(): void {
    setTimeout(() => this.staticAlertClosed = true, 20000);

    this._success.subscribe(message => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = '');
    this.experimentService.getHosts().subscribe(data => {
        this.hosts = data;
        console.log(this.hosts);
      }, error => {
        console.log(error);
      }
    );
  }

  remove(host: any): void {
    this.experimentService.removeHOst(host).subscribe(data => {
      this.type = 'success';
      this._success.next(`Host successfully removed`);
      this.hosts = data;
      }, error => {
      this.type = 'danger';
      this._success.next(`Cannot remove hosts which are being used already!`);
      }
    );
  }

}
