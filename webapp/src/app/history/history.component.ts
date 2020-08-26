import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ExperimentService} from '../experiment.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-flag-content',
  template: `
    <mat-card>
      <mat-card-title>Flags</mat-card-title>
      <div class="mat-elevation-z8">
        <table class="table table-striped">
          <thead>
          <tr>
            <th scope="col">Flag</th>
            <th scope="col">Value</th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let item of flags; index as i">
            <td>
              {{ item.experimentFlag }}
            </td>
            <td>{{ item.value }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </mat-card>
  `
})
export class FlagsComponent {
  @Input() flags;

  constructor(public activeModal: NgbActiveModal) {}
}

@Component({
  selector: 'app-matcher-content',
  template: `
    <mat-card>
      <mat-card-title>Matchers</mat-card-title>
      <div class="mat-elevation-z8">
        <table class="table table-striped">
          <thead>
          <tr>
            <th scope="col">Matcher</th>
            <th scope="col">Value</th>
          </tr>
          </thead>
          <tbody>
          <tr *ngFor="let item of matchers; index as i">
            <td>
              {{ item.experimentMatcher }}
            </td>
            <td>{{ item.value }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </mat-card>
  `
})
export class MatchersComponent {
  @Input() matchers;

  constructor(public activeModal: NgbActiveModal) {}
}

@Component({
  selector: 'app-hiost',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})

export class HistoryComponent implements OnInit {

  public records;
  public type;
  // tslint:disable-next-line:variable-name
  private _success = new Subject<string>();

  staticAlertClosed = false;
  successMessage = '';
  constructor(private experimentService: ExperimentService, private modalService: NgbModal) {

  }

  ngOnInit(): void {
    setTimeout(() => this.staticAlertClosed = true, 20000);

    this._success.subscribe(message => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = '');
    this.experimentService.getExperimentRecords().subscribe(data => {
        this.records = data;
      }, error => {
        console.log(error);
      }
    );
  }

  openFlags(flagList: any): void {
    const modalRef = this.modalService.open(FlagsComponent);
    console.log(flagList)
    modalRef.componentInstance.flags = flagList;
  }

  openMatchers(matcherList: any): void {
    const modalRef = this.modalService.open(MatchersComponent);
    modalRef.componentInstance.matchers = matcherList;
  }

  destroyExperiment(experiment: any): void {
    this.experimentService.destroyExperiment(experiment).subscribe(data => {
      this.type = 'success';
      this._success.next(`Experiment successfully destroyed.`);
      this.experimentService.getExperimentRecords().subscribe(records => {
          this.records = records;
        }, error => {
          console.log(error);
        }
      );
      }, error => {
      this.type = 'danger';
      this._success.next(`Problem when destroying the experiment!`);
      }
    );
  }

  reCreateExperiment(experiment: any): void {
    let expInfo = {
      target: experiment.target,
      action: experiment.action,
      hostID:  experiment.host.hostID,
      scope: '',
      flags: [],
      matchers: []
    };
    for ( const i of experiment.flags){
      const flag = {
        flagName: i.experimentFlag,
        flagValue: i.value
      }
      expInfo.flags.push(flag);
    }
    for ( const i of experiment.matchers){
      const flag = {
        matcherName: i.experimentMatcher,
        matcherValue: i.value
      }
      expInfo.flags.push(flag);
    }
    this.experimentService.createExperiment(expInfo).subscribe(data => {
        this.type = 'success';
        this._success.next(`Experiment successfully recreated!`);
        this.experimentService.getExperimentRecords().subscribe(records => {
            this.records = records;
          }, error => {
            console.log(error);
          }
        );
      }, error => {
        this.type = 'danger';
        this._success.next(`Problem when recreating experiment!`);
      }
    );
  }

}
