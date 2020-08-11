import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ExperimentService } from '../experiment.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  name = 'Angular';
  public targetList = [];
  public actionList = [];
  public matcherList = [];
  public flagList = [];
  public scopeList = ['host', 'docker', 'node', 'pod'];
  public userForm: FormGroup;
  public createExperimentForm: FormGroup;
  experimentModel;

  // tslint:disable-next-line:variable-name
  constructor(private _fb: FormBuilder, private experimentService: ExperimentService) {
    this.createExperimentForm = this._fb.group({
      target: [],
      action: [],
      host: [],
      scope: [],
      matchers: this._fb.array([]),
      flags: this._fb.array([])
    });
  }

  private addMatcherGroup(): FormGroup {
    return this._fb.group({
      matcherName: [],
      matcherValue: []
    });
  }

  private addFlagGroup(): FormGroup {
    return this._fb.group({
      flagName: [],
      flagValue: []
    });
  }

  addMatcher(): void {
    if (this.matcherArray.length < this.matcherList.length){
      this.matcherArray.push(this.addMatcherGroup());
    }else {
      console.log('No available matchers');
    }
  }

  addFlag(): void {
    console.log(this.createExperimentForm.value);
    if (this.flagArray.length < this.flagList.length){
      this.flagArray.push(this.addFlagGroup());
    }else {
      console.log('No available flags');
    }
  }


  removeMatcher(index: number): void {
    this.matcherArray.removeAt(index);
  }

  removeFlag(index: number): void {
    this.flagArray.removeAt(index);
  }

  get matcherArray(): FormArray {
    return this.createExperimentForm.get('matchers') as FormArray;
  }

  get flagArray(): FormArray {
    return this.createExperimentForm.get('flags') as FormArray;
  }

  testChange(): void {
    console.log(this.userForm.value);
  }

  changeActionList(): void {
    // tslint:disable-next-line:prefer-for-of
    this.actionList = [];
    const target = this.createExperimentForm.value.target;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.experimentModel.length; i++){
      const experimentObject = this.experimentModel[i];
      console.log(experimentObject.scope);
      if (experimentObject.target === target && experimentObject.scope === 'host'){
        // tslint:disable-next-line:forin prefer-for-of
        console.log(experimentObject.actions);
        for (let j = 0; j < experimentObject.actions.length; j++){
          this.actionList.push(experimentObject.actions[j].action);
        }
      }
    }

  }

  changeFlagList(): void {
    this.flagList = []
    const action = this.createExperimentForm.value.action;
    const target = this.createExperimentForm.value.target;
    const scope = this.createExperimentForm.value.scope;
    console.log(scope);
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.experimentModel.length; i++){
      const experimentObject = this.experimentModel[i];
      if (experimentObject.target === target && experimentObject.scope === scope){
        // tslint:disable-next-line:forin prefer-for-of
        for (let j = 0; j < experimentObject.actions.length; j++){
          if (experimentObject.actions[j].action === action){
            this.flagList = experimentObject.actions[j].flags;
            if (experimentObject.actions[j].matchers === null){
              this.matcherList = [];
            }else {
              this.matcherList = experimentObject.actions[j].matchers;
            }
          }
        }
      }
    }
  }

  ngOnInit(): void {
    this.experimentService.getExperiments().subscribe(data => {
        this.experimentModel = data
        this.setTargetList();
      }
    );
  }

  private setTargetList(): void {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.experimentModel.length; i++){
      const experimentObject = this.experimentModel[i];
      this.targetList.push(experimentObject.target);
    }
  }

  public updateDisabilityFlag(index: number): void {
    // tslint:disable-next-line:forin
    for (const i in this.flagList){
      if (this.flagList[i].name === this.flagArray.at(index).value.flagName){
        if (this.flagList[i].noArgs) {
          this.flagArray.at(index)['controls']['flagValue'].disable();
        } else {
          this.flagArray.at(index)['controls']['flagValue'].enable();
        }
      }
    }

  }

  public updateDisabilityMatcher(index: number): void {
    // tslint:disable-next-line:forin
    for (const i in this.matcherList){
      if (this.matcherList[i].name === this.matcherArray.at(index).value.matcherName){
        if (this.matcherList[i].noArgs) {
          this.matcherArray.at(index)['controls']['matcherValue'].disable();
        } else {
          this.matcherArray.at(index)['controls']['matcherValue'].enable();
        }
      }
    }

  }

}
