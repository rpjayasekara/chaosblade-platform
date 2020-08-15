import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ExperimentService} from '../experiment.service';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';


@Component({
  selector: 'app-addhost',
  templateUrl: './addhost.component.html',
  styleUrls: ['./addhost.component.css']
})
export class AddhostComponent implements OnInit {

  public addHostForm: FormGroup;
  public type;
  // tslint:disable-next-line:variable-name
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage = '';
  // tslint:disable-next-line:variable-name
  constructor(private _fb: FormBuilder, private experimentService: ExperimentService) {
    this.addHostForm = this._fb.group({
      hostName: ['', [Validators.required]],
      hostIP: ['', [Validators.required]],
      hostPort: ['', [Validators.required]]
    });
  }

  public addHost(): void {
    if (this.addHostForm.valid){
      this.experimentService.addHost(this.addHostForm.value).subscribe(data => {
          this.type = 'success';
          this._success.next(`Host successfully added!`);
        }, error => {
        this.type = 'danger';
        this._success.next(`Failed! Host adding`);
        }
      );
    } else {
      this.type = 'danger';
      this._success.next(`Please fill the required fields!`);
    }
  }

  ngOnInit(): void {
    setTimeout(() => this.staticAlertClosed = true, 20000);

    this._success.subscribe(message => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = '');
  }

}
