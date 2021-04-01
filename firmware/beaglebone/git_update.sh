#!/bin/bash

# clones desired repo and and checkouts branch if dir doesn't exist, or checkout branch and pull to update
# based on configuration defined below

# configuration variables
remote_protocol=https://                            # web protocol to access remote repo with
git_remote=git.ece.iastate.edu/sd/sddec21-07.git    # remote repo url (excluding https:// | http://)
git_tracking_branch=dev                             # branch to track
git_user_name=sddec21_proj07                        # username to access repo with
git_user_token=yzA6bprgqTyVcfSraFND                 # pwd/token to verify access with
git_remote_filter=firmware/beaglebone               # files to check out from repo
git_local_dir_abs=/var/lib/cloud9/autogit           # local directory repo is located at (absolute path)


# start script
echo -e "updating/creating repo for sddec21_proj07...\n";

while ! ip route | grep -oP 'default via .+ dev wlan0'; do

  echo -e "...waiting for network connection...\n"
  sleep 1
  
done

# check if local repo dir exists. if so, pull from git_remote. else, 
# if true, pull from remote
# else, clone git_remote/git_remote_dir into local_dir_abs
if [ -d $git_local_dir_abs ]; then

    cd $git_local_dir_abs
    
    echo -e "username=$git_user_name\r\npassword=$git_user_token"  >> ./.gitcredentials
    git config --local credential.helper 'store --file ./.gitcredentials'
    git config --local core.sparsecheckout true
    echo "firmware/beaglebone/*" >> .git/info/sparse-checkout
    git reset --hard HEAD
    git checkout $git_tracking_branch
    
    git pull
    
    echo -e "...updated sddec_proj07 firmware from origin/$git_tracking_branch\n";
else

    mkdir $git_local_dir_abs && cd $git_local_dir_abs
    echo -e "...created dir $git_local_dir_abs...\n"
    
    git init
    echo -e "username=$git_user_name\r\npassword=$git_user_token"  >> ./.gitcredentials
    git config --local credential.helper 'store --file ./.gitcredentials'
    git remote add origin -f $remote_protocol$git_user_name:$git_user_token@$git_remote
    git config --local core.sparsecheckout true
    echo "firmware/beaglebone/*" >> ./.git/info/sparse-checkout
    git pull origin $git_tracking_branch
    
    echo -e "...checked out [$] from origin/$git_tracking_branch\n"
fi

exit;