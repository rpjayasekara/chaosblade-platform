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
            <th scope="col">uid</th>
            <th scope="col">target</th>
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
            <th scope="col">uid</th>
            <th scope="col">target</th>
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
      this._success.next(`Experiment successfully deleted.`);
      this.experimentService.getExperimentRecords().subscribe(records => {
          this.records = records;
        }, error => {
          console.log(error);
        }
      );
      }, error => {
        console.log(error);
      }
    );
  }

}
